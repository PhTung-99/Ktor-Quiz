package dev.timpham.features.user.repository

import dev.timpham.data.models.BaseResponse
import dev.timpham.data.features.user.models.User
import io.ktor.http.*
import java.util.UUID

interface UserRepository {
    suspend fun getUserInfo(userId: UUID): Pair<HttpStatusCode, BaseResponse<User?>>
}