package dev.timpham.data.features.user.dao

import dev.timpham.data.features.user.models.User
import java.util.UUID

interface UserDAO {
    suspend fun emailUsed(email:String): Boolean
    suspend fun createUser(email: String, name: String, password: String, avatar: String?): User
    suspend fun getUserByEmail(email: String): User?
    suspend fun getUserById(id: UUID): User?
}