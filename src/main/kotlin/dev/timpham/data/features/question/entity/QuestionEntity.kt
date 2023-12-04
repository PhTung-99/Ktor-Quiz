package dev.timpham.data.features.question.entity

import dev.timpham.data.database.BaseEntity
import dev.timpham.data.features.quiz.entity.QuizEntity

object QuestionEntity: BaseEntity("question") {
    val content = varchar("name", 500)
    val highlight = varchar("highlight", 50).nullable()
    val score = integer("score")
    val quizId = uuid("quiz_id").references(QuizEntity.id)
}