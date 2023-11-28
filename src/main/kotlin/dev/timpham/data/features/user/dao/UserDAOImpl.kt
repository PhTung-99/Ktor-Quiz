package dev.timpham.data.features.user.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.user.entity.UserEntity
import dev.timpham.data.features.user.entity.UserTokenEntity
import dev.timpham.data.features.user.mapper.resultRowToUser
import dev.timpham.data.features.user.mapper.resultRowToUserWithPassword
import dev.timpham.data.features.user.mapper.resultRowToUserToken
import dev.timpham.data.features.user.models.User
import dev.timpham.data.features.user.models.UserToken
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.util.*

class UserDAOImpl: UserDAO {

    override suspend fun emailUsed(email: String): Boolean = dbQuery {
        val useEmail = UserEntity.select {
            UserEntity.email eq email
        }
        return@dbQuery useEmail.count() > 0L
    }

    override suspend fun createUser(email: String, name: String, password: String, avatar: String?): User? = dbQuery {
        val createStatement = UserEntity.insert {
            it[UserEntity.name] = name
            it[UserEntity.email] = email
            it[UserEntity.password] = password
            it[UserEntity.avatar] = avatar
        }
        createStatement.resultedValues?.singleOrNull()?.let(::resultRowToUserWithPassword)
    }

    override suspend fun getUserByEmail(email: String): User? = dbQuery {
        val user = UserEntity.select {
            UserEntity.email eq email
        }
        return@dbQuery user.singleOrNull()?.let(::resultRowToUserWithPassword)
    }

    override suspend fun getUserById(id: UUID): User? = dbQuery {
        val user = UserEntity.select {
            UserEntity.id eq id
        }
        return@dbQuery user.singleOrNull()?.let(::resultRowToUser)
    }

    override suspend fun saveRefreshToken(userId: UUID, refreshToken: String): UserToken? = dbQuery {
        val createStatement = UserTokenEntity.insert {
            it[UserTokenEntity.userId] = userId
            it[UserTokenEntity.refreshToken] = refreshToken
        }
        createStatement.resultedValues?.singleOrNull()?.let(::resultRowToUserToken)
    }
}