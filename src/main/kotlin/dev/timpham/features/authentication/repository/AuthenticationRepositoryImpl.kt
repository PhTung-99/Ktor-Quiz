package dev.timpham.features.authentication.repository

import dev.timpham.authentication.JWTUtils
import dev.timpham.common.alias.ResponseAlias
import dev.timpham.common.constants.Constants
import dev.timpham.common.models.BaseResponse
import dev.timpham.data.features.user.dao.UserDAO
import dev.timpham.data.features.user.dao.UserTokenDAO
import dev.timpham.data.features.user.models.User
import dev.timpham.data.models.JWTToken
import dev.timpham.data.redis.RedisClient
import dev.timpham.features.authentication.constants.AuthenticationMessageCode
import dev.timpham.features.authentication.models.requests.LoginRequest
import dev.timpham.features.authentication.models.requests.SignupRequest
import dev.timpham.features.authentication.models.responses.LoginResponse
import dev.timpham.utils.saveImage
import io.ktor.http.*
import org.mindrot.jbcrypt.BCrypt
import java.io.File
import java.util.UUID

class AuthenticationRepositoryImpl(
    private val userDAO: UserDAO,
    private val userTokenDAO: UserTokenDAO,
): AuthenticationRepository {

    private suspend fun isEmailAvailable(email: String): Boolean {
        return !userDAO.emailUsed(email)
    }

    override suspend fun signup(
        signupRequest: SignupRequest,
        fileByte: ByteArray?,
        originalFileName: String?,
    ): ResponseAlias<User?> {
        return if (isEmailAvailable(signupRequest.email)) {
            val hashedPassword = BCrypt.hashpw(signupRequest.password, BCrypt.gensalt())
            var avatar: String? = null
            if (fileByte != null && originalFileName != null) {
                avatar = saveImage(fileByte, originalFileName)
            }

            try {
                val user = userDAO.createUser(
                    email = signupRequest.email,
                    name = signupRequest.name,
                    password = hashedPassword,
                    avatar = avatar
                )
                Pair(
                    HttpStatusCode.Created,
                    BaseResponse(
                        data = user,
                        messageCode = AuthenticationMessageCode.SIGNUP_SUCCESS
                    )
                )
            }
            catch (e: Exception) {
                avatar?.let {
                    File("${Constants.USER_IMAGES_PATH}/$avatar").delete()
                }
                Pair(
                    HttpStatusCode.InternalServerError,
                    BaseResponse(
                        messageCode = e.message
                    )
                )
            }
        }
        else {
            Pair(
                HttpStatusCode.BadRequest,
                BaseResponse(
                    data = null,
                    messageCode = AuthenticationMessageCode.EMAIL_USED
                )
            )
        }
    }

    override suspend fun login(loginRequest: LoginRequest): ResponseAlias<LoginResponse?> {
        val user = userDAO.getUserByEmail(loginRequest.email)
        return if (user != null) {
            val isPasswordCorrect = BCrypt.checkpw(loginRequest.password, user.password)
            return if (isPasswordCorrect) {
                return onLogin(user)
            }
            else {
                Pair(
                    HttpStatusCode.BadRequest,
                    BaseResponse(
                        messageCode = AuthenticationMessageCode.PASSWORD_WRONG
                    )
                )
            }
        }
        else {
            Pair(
                HttpStatusCode.BadRequest,
                BaseResponse(
                    messageCode = AuthenticationMessageCode.EMAIL_NOT_FOUND
                )
            )
        }
    }

    override suspend fun refreshToken(
        userId: UUID,
        refreshToken: String,
    ): ResponseAlias<LoginResponse?> {
        val isRefreshValid = JWTUtils.isTokenValid(refreshToken)
        if (isRefreshValid) {
            val lastToken = userTokenDAO.getRefreshTokenByUserId(userId)
            val user = userDAO.getUserById(userId)
            if (lastToken != null && user != null) {
                if (lastToken.refreshToken == refreshToken) {
                    return onLogin(user)
                }
            }
        }

        return Pair(
            HttpStatusCode.BadRequest,
            BaseResponse(
                messageCode = AuthenticationMessageCode.INVALID_REFRESH_TOKEN
            )
        )
    }

    override suspend fun logout(token: String, refreshToken: String): ResponseAlias<Boolean> {
        return try {
            val isRefreshValid = JWTUtils.isTokenValid(refreshToken)
            if (isRefreshValid) {
                RedisClient.jedis.setex(token, JWTUtils.validityInMs,"revoked")
                RedisClient.jedis.setex(refreshToken, JWTUtils.validityInMs,"revoked")
                Pair(HttpStatusCode.OK, BaseResponse(data = true))
            } else {
                Pair(HttpStatusCode.BadRequest, BaseResponse(data = false, messageCode = AuthenticationMessageCode.INVALID_REFRESH_TOKEN))
            }
        } catch (e: Exception) {
            Pair(HttpStatusCode.InternalServerError, BaseResponse(data = false, messageCode = e.message))
        }
    }

    private suspend fun saveTokenToDB(refreshToken: String, userId: UUID) {
        userDAO.saveRefreshToken(userId, refreshToken)
    }

    private suspend fun onGenerateToken(user: User): JWTToken {
        val accessToken = JWTUtils.generateToken(user)
        val refreshToken = JWTUtils.generateReToken(user)
        saveTokenToDB(userId = user.id, refreshToken = refreshToken)
        return JWTToken(accessToken = accessToken, refreshToken = refreshToken)
    }

    private suspend fun onLogin(user: User): ResponseAlias<LoginResponse?> {
        return try {
            val jwtToken = onGenerateToken(user)
            val loginResponse = LoginResponse(
                user = user,
                token = jwtToken,
            )
            Pair(
                HttpStatusCode.OK,
                BaseResponse(
                    data = loginResponse,
                )
            )
        }
        catch (e: Exception) {
            Pair(
                HttpStatusCode.InternalServerError,
                BaseResponse()
            )
        }
    }
}