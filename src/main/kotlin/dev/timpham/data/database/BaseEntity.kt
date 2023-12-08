package dev.timpham.data.database

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

abstract class BaseEntity(id: EntityID<UUID>, table: BaseTable): UUIDEntity(id) {
    var createdAtUTC by table.createdAtUTC
    var isDeleted by table.isDeleted
}