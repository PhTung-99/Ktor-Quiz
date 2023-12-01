package dev.timpham.data.features.question.entity

import dev.timpham.data.database.datetypes.timestampWithTimeZone
import dev.timpham.data.features.quiz.entity.QuizEntity
import org.jetbrains.exposed.dao.id.UUIDTable
import java.time.Instant

object QuestionEntity: UUIDTable("question") {
    val content = varchar("name", 500)
    val highlight = varchar("highlight", 50).nullable()
    val quizId = uuid("quiz_id").references(QuizEntity.id)
    val createAtUTC = timestampWithTimeZone("created_at_UTC").default(Instant.now())
    val isDeleted = bool("is_deleted").default(defaultValue = false)
}