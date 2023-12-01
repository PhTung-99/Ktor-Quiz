package dev.timpham.features.user.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.data.features.user.models.User
import java.util.*

interface UserRepository {
    suspend fun getUserInfo(userId: UUID): ResponseAlias<User?>
}