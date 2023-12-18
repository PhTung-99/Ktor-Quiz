package dev.timpham.data.features.userRoles.entity

import dev.timpham.data.database.BaseEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class UserRoleEntity(id: EntityID<UUID>): BaseEntity(id, UserRoles) {
    companion object : UUIDEntityClass<UserRoleEntity>(UserRoles)

    var userId by UserRoles.userId
    var roleId by UserRoles.roleId
}