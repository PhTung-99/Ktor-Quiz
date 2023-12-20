package dev.timpham.data.features.user.mapper

import dev.timpham.common.constants.Constants
import dev.timpham.data.features.user.entity.UserEntity
import dev.timpham.data.features.user.models.User

fun entityToUserWithPassword(entity: UserEntity) = User(
    id = entity.id.value,
    name = entity.name,
    email = entity.email,
    password = entity.password,
    createAtUTC = entity.createdAtUTC,
    avatar = entity.avatar?.let {
        return@let Constants.USER_IMAGES_ROUTE + entity.avatar
    },
    role = entity.role,
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
    role = entity.role,
    isDeleted = entity.isDeleted,
)
