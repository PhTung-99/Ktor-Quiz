package dev.timpham.data.features.question.entity

import dev.timpham.data.database.BaseEntity
import dev.timpham.data.features.answer.entity.AnswerEntity
import dev.timpham.data.features.answer.entity.Answers
import dev.timpham.data.features.quiz.entity.QuizEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class QuestionEntity(id: EntityID<UUID>): BaseEntity(id, Questions) {
    companion object : UUIDEntityClass<QuestionEntity>(Questions)

    var content by Questions.content
    var highlight by Questions.highlight
    var isMultipleChoice by Questions.isMultipleChoice
    var score by Questions.score
    var quiz by QuizEntity referencedOn Questions.quiz

    val answers by AnswerEntity referrersOn Answers.question
}