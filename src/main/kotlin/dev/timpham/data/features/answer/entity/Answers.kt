package dev.timpham.data.features.answer.entity

import dev.timpham.data.database.BaseTable
import dev.timpham.data.features.question.entity.Questions

object Answers: BaseTable("answer") {
    val content = varchar("content", 500)
    val isCorrect = bool("is_correct")
    val question = reference("question", Questions)
}
