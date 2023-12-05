package dev.timpham.data.features.question.entity

import dev.timpham.data.database.BaseTable
import dev.timpham.data.features.quiz.entity.QuizEntity

object QuestionEntity: BaseTable("question") {
    val content = varchar("name", 500)
    val highlight = varchar("highlight", 50).nullable()
    val score = integer("score")
    val quiz = reference("quiz", QuizEntity)
}