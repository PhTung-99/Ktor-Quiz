package dev.timpham.data.features.user.dao


import dev.timpham.data.features.user.models.UserToken
import java.util.UUID

interface UserTokenDAO {
    suspend fun getRefreshTokenByUserId(userId: UUID): UserToken?

}