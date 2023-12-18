package dev.timpham.data.features.userTokens.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.user.entity.UserEntity
import dev.timpham.data.features.userTokens.entity.UserTokenEntity
import dev.timpham.data.features.userTokens.entity.UserTokens
import dev.timpham.data.features.userTokens.mapper.entityToUserToken
import dev.timpham.data.features.userTokens.models.UserToken
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.select
import java.util.*

class UserTokenDAOImpl: UserTokenDAO {
    override suspend fun getRefreshTokenByUserId(userId: UUID): UserToken? = dbQuery {
        UserTokens.select {
            UserTokens.user eq userId
        }
            .orderBy(UserTokens.createdAtUTC, SortOrder.DESC)
            .limit(1)
            .singleOrNull()?.let {
                UserTokenEntity.wrapRow(it).let(::entityToUserToken)
            }
    }

    override suspend fun saveRefreshToken(userId: UUID, refreshToken: String): UserToken = dbQuery {
        UserTokenEntity.new {
            this.user = UserEntity[userId]
            this.refreshToken = refreshToken
        }.let(::entityToUserToken)
    }
}