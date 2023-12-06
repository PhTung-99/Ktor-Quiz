package dev.timpham.data.features.answers.entity

import dev.timpham.data.database.BaseEntity
import dev.timpham.data.features.answers.models.Answer
import dev.timpham.data.features.question.entity.QuestionEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class AnswerEntity(id: EntityID<UUID>): BaseEntity(id, Answers) {
    companion object : UUIDEntityClass<AnswerEntity>(Answers)
    var content by Answers.content
    var isCorrect by Answers.isCorrect
    var question by QuestionEntity referencedOn Answers.question

    fun toAnswer(): Answer {
        return Answer(
            id = id.value,
            content = content,
            isCorrect = isCorrect,
            questionId = question.id.value,
            createdAtUTC = createdAtUTC,
            isDeleted = isDeleted,
        )
    }
}