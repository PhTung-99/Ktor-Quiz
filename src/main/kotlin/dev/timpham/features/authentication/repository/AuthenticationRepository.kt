package dev.timpham.features.authentication.repository

import dev.timpham.data.models.BaseResponse
import dev.timpham.data.features.user.models.User
import dev.timpham.features.authentication.models.requests.LoginRequest
import dev.timpham.features.authentication.models.requests.SignupRequest
import dev.timpham.features.authentication.models.responses.LoginResponse
import io.ktor.http.*
import java.util.UUID


interface AuthenticationRepository {

    suspend fun signup(
        signupRequest: SignupRequest,
        fileByte: ByteArray?,
        originalFileName: String?,
    ): Pair<HttpStatusCode,BaseResponse<User?>>
    suspend fun login(loginRequest: LoginRequest): Pair<HttpStatusCode,BaseResponse<LoginResponse?>>

    suspend fun refreshToken(
        userId: UUID,
        refreshToken: String,
    ): Pair<HttpStatusCode,BaseResponse<LoginResponse?>>

    suspend fun logout(
        token: String,
        refreshToken: String,
    ): Pair<HttpStatusCode, BaseResponse<Boolean>>
}