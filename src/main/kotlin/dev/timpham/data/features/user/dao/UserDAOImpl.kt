package dev.timpham.data.features.user.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.user.entity.UserEntity
import dev.timpham.data.features.user.entity.Users
import dev.timpham.data.features.user.mapper.entityToUser
import dev.timpham.data.features.user.mapper.entityToUserWithPassword
import dev.timpham.data.features.user.models.User
import java.util.*

class UserDAOImpl: UserDAO {

    override suspend fun emailUsed(email: String): Boolean = dbQuery {
        val useEmail = UserEntity.find {
            Users.email eq email
        }
        return@dbQuery useEmail.count() > 0L
    }

    override suspend fun createUser(email: String, name: String, password: String, avatar: String?): User = dbQuery {
        UserEntity.new {
            this.name = name
            this.email = email
            this.password = password
            this.avatar = avatar
        }.let(::entityToUser)
    }

    override suspend fun getUserByEmail(email: String): User? = dbQuery {
        UserEntity.find {
            Users.email eq email
        }.firstOrNull()?.let(::entityToUserWithPassword)
    }

    override suspend fun getUserById(id: UUID): User? = dbQuery {
        UserEntity.findById(id)?.let(::entityToUser)
    }

}