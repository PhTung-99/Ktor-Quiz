package dev.timpham.data.features.userTokens.mapper

import dev.timpham.data.features.userTokens.entity.UserTokenEntity
import dev.timpham.data.features.userTokens.models.UserToken

fun entityToUserToken(entity: UserTokenEntity) = UserToken(
    id = entity.id.value,
    refreshToken = entity.refreshToken,
    userId =  entity.user.id.value,
    createAtUTC = entity.createAtUTC,
)