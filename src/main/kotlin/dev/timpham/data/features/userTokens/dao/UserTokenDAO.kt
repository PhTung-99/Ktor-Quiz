package dev.timpham.data.features.userTokens.dao


import dev.timpham.data.features.userTokens.models.UserToken
import java.util.*

interface UserTokenDAO {
    suspend fun getRefreshTokenByUserId(userId: UUID): UserToken?

    suspend fun saveRefreshToken(userId: UUID, refreshToken: String): UserToken
}