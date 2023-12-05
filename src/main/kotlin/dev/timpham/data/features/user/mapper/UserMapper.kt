package dev.timpham.data.features.user.mapper

import dev.timpham.common.constants.Constants
import dev.timpham.data.features.user.entity.UserTable
import dev.timpham.data.features.user.entity.UserTokenTable
import dev.timpham.data.features.user.models.User
import dev.timpham.data.features.user.models.UserToken
import org.jetbrains.exposed.sql.ResultRow


fun resultRowToUserWithPassword(row: ResultRow) = User(
    id = row[UserTable.id].value,
    name = row[UserTable.name],
    email = row[UserTable.email],
    password = row[UserTable.password],
    createAtUTC = row[UserTable.createdAtUTC],
    avatar = row[UserTable.avatar]?.let {
        return@let Constants.USER_IMAGES_ROUTE + row[UserTable.avatar]
    },
    isDeleted = row[UserTable.isDeleted],
)

fun resultRowToUser(row: ResultRow) = User(
    id = row[UserTable.id].value,
    name = row[UserTable.name],
    email = row[UserTable.email],
    createAtUTC = row[UserTable.createdAtUTC],
    avatar = row[UserTable.avatar]?.let {
     return@let Constants.USER_IMAGES_ROUTE + row[UserTable.avatar]
    },
    isDeleted = row[UserTable.isDeleted],
)

fun resultRowToUserToken(row: ResultRow) = UserToken(
    id = row[UserTokenTable.id].value,
    refreshToken = row[UserTokenTable.refreshToken],
    userId =  row[UserTokenTable.userId].value,
    createAtUTC = row[UserTokenTable.createAtUTC],
)