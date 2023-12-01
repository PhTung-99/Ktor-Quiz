package dev.timpham.features.authentication.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.data.features.user.models.User
import dev.timpham.features.authentication.models.requests.LoginRequest
import dev.timpham.features.authentication.models.requests.SignupRequest
import dev.timpham.features.authentication.models.responses.LoginResponse
import java.util.*


interface AuthenticationRepository {

    suspend fun signup(
        signupRequest: SignupRequest,
        fileByte: ByteArray?,
        originalFileName: String?,
    ): ResponseAlias<User?>
    suspend fun login(loginRequest: LoginRequest): ResponseAlias<LoginResponse?>

    suspend fun refreshToken(
        userId: UUID,
        refreshToken: String,
    ): ResponseAlias<LoginResponse?>

    suspend fun logout(
        token: String,
        refreshToken: String,
    ): ResponseAlias<Boolean>
}