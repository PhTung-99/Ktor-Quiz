package dev.timpham.data.features.user.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.user.entity.UserTable
import dev.timpham.data.features.user.entity.UserTokenTable
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
        val useEmail = UserTable.select {
            UserTable.email eq email
        }
        return@dbQuery useEmail.count() > 0L
    }

    override suspend fun createUser(email: String, name: String, password: String, avatar: String?): User? = dbQuery {
        val createStatement = UserTable.insert {
            it[UserTable.name] = name
            it[UserTable.email] = email
            it[UserTable.password] = password
            it[UserTable.avatar] = avatar
        }
        createStatement.resultedValues?.singleOrNull()?.let(::resultRowToUserWithPassword)
    }

    override suspend fun getUserByEmail(email: String): User? = dbQuery {
        val user = UserTable.select {
            UserTable.email eq email
        }
        return@dbQuery user.singleOrNull()?.let(::resultRowToUserWithPassword)
    }

    override suspend fun getUserById(id: UUID): User? = dbQuery {
        val user = UserTable.select {
            UserTable.id eq id
        }
        return@dbQuery user.singleOrNull()?.let(::resultRowToUser)
    }

    override suspend fun saveRefreshToken(userId: UUID, refreshToken: String): UserToken? = dbQuery {
        val createStatement = UserTokenTable.insert {
            it[UserTokenTable.userId] = userId
            it[UserTokenTable.refreshToken] = refreshToken
        }
        createStatement.resultedValues?.singleOrNull()?.let(::resultRowToUserToken)
    }
}