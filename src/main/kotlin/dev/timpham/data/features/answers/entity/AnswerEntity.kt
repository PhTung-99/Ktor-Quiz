package dev.timpham.data.features.answers.entity

import dev.timpham.data.database.BaseTable
import dev.timpham.data.features.question.entity.QuestionEntity

object AnswerEntity: BaseTable("answer") {
    val content = varchar("content", 500)
    val isCorrect = bool("is_correct")
    val questionId = uuid("question_id").references(QuestionEntity.id)
}