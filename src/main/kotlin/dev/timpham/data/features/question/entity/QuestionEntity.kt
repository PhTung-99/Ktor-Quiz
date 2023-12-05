package dev.timpham.data.features.question.entity

import dev.timpham.data.database.BaseEntity
import dev.timpham.data.features.question.models.Question
import dev.timpham.data.features.quiz.entity.QuizEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class QuestionEntity(id: EntityID<UUID>): BaseEntity(id, Questions) {
    companion object : UUIDEntityClass<QuestionEntity>(Questions)

    var content by Questions.content
    var highlight by Questions.highlight
    var score by Questions.score
    var quiz by QuizEntity referencedOn Questions.quiz

    fun toQuestion(): Question {
        return Question(
            id = id.value,
            content = content,
            highlight = highlight,
            score = score,
            quizId = quiz.id.value,
            answers = listOf(),
            createdAtUTC = createdAtUTC,
            isDeleted = isDeleted,
        )
    }
}