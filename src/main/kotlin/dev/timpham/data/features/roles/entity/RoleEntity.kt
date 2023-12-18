package dev.timpham.data.features.roles.entity

import dev.timpham.data.database.BaseEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class RoleEntity(id: EntityID<UUID>): BaseEntity(id, Roles) {
    companion object : UUIDEntityClass<RoleEntity>(Roles)

    var name by Roles.name
    var description by Roles.description
}