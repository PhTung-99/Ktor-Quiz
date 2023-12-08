package dev.timpham.data.features.user.mapper

import dev.timpham.common.constants.Constants
import dev.timpham.data.features.user.entity.UserEntity
import dev.timpham.data.features.user.entity.UserTokens
import dev.timpham.data.features.user.models.User
import dev.timpham.data.features.user.models.UserToken
import org.jetbrains.exposed.sql.ResultRow


fun entityToUserWithPassword(entity: UserEntity) = User(
    id = entity.id.value,
    name = entity.name,
    email = entity.email,
    password = entity.password,
    createAtUTC = entity.createdAtUTC,
    avatar = entity.avatar?.let {
        return@let Constants.USER_IMAGES_ROUTE + entity.avatar
    },
    isDeleted = entity.isDeleted,
)

fun entityToUser(entity: UserEntity) = User(
    id = entity.id.value,
    name = entity.name,
    email = entity.email,
    createAtUTC = entity.createdAtUTC,
    avatar = entity.avatar?.let {
        return@let Constants.USER_IMAGES_ROUTE + entity.avatar
    },
    isDeleted = entity.isDeleted,
)

fun resultRowToUserToken(row: ResultRow) = UserToken(
    id = row[UserTokens.id].value,
    refreshToken = row[UserTokens.refreshToken],
    userId =  row[UserTokens.userId].value,
    createAtUTC = row[UserTokens.createAtUTC],
)