package dev.timpham.data.features.user.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.user.entity.UserTokens
import dev.timpham.data.features.user.mapper.resultRowToUserToken
import dev.timpham.data.features.user.models.UserToken
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.select
import java.util.*

class UserTokenDAOImpl: UserTokenDAO {
    override suspend fun getRefreshTokenByUserId(userId: UUID): UserToken? {
        return dbQuery {
            UserTokens.select {
                UserTokens.userId eq userId
            }
            .orderBy(UserTokens.createAtUTC, SortOrder.DESC)
            .limit(1)
            .singleOrNull()?.let(::resultRowToUserToken)
        }
    }
}