package dev.timpham.data.features.question.entity

import dev.timpham.data.database.BaseTable
import dev.timpham.data.features.quiz.entity.Quizzes

object Questions: BaseTable("question") {
    val content = varchar("name", 500)
    val highlight = varchar("highlight", 50).nullable()
    val isMultipleChoice = bool("is_multiple_choice")
    val score = integer("score")
    val quiz = reference("quiz", Quizzes)
}
