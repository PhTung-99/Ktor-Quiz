package dev.timpham.data.features.userTokens.entity

import dev.timpham.data.database.BaseEntity
import dev.timpham.data.features.user.entity.UserEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class UserTokenEntity(id: EntityID<UUID>): BaseEntity(id, UserTokens) {
    companion object : UUIDEntityClass<UserTokenEntity>(UserTokens)

    var user by UserEntity referencedOn UserTokens.user
    var refreshToken by UserTokens.refreshToken
    var createAtUTC by UserTokens.createdAtUTC
}