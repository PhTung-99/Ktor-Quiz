package dev.timpham.data.features.quiz.entity

import dev.timpham.data.database.datetypes.timestampWithTimeZone
import org.jetbrains.exposed.dao.id.UUIDTable
import java.time.Instant

object QuizEntity: UUIDTable("quiz") {
    val name = varchar("name", 50)
    val description = varchar("description", 200)
    val isActive = bool("is_active")
    val createAtUTC = timestampWithTimeZone("created_at_UTC").default(Instant.now())
    val isDeleted = bool("is_deleted").default(defaultValue = false)
}