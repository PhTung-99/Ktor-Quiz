package dev.timpham.data.database

import dev.timpham.data.database.datetypes.timestampWithTimeZone
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import java.time.Instant
import java.util.UUID

open class BaseTable(name: String = "", columnName: String = "id"): IdTable<UUID>(name) {
    final override val id: Column<EntityID<UUID>> = uuid(columnName).autoGenerate().entityId()
    final override val primaryKey = PrimaryKey(id)
    val createAtUTC = timestampWithTimeZone("created_at_UTC").default(Instant.now())
    val isDeleted = bool("is_deleted").default(defaultValue = false)
}