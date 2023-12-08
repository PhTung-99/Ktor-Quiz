package dev.timpham.data.features.userAnswerHistory.entity

import dev.timpham.data.database.BaseTable
import dev.timpham.data.features.answers.entity.Answers
import dev.timpham.data.features.question.entity.Questions
import dev.timpham.data.features.user.entity.Users

object UserAnswerHistory: BaseTable("user_answer_history") {
    val user = reference("user", Users)
    val question = reference("question", Questions)
    val answer = reference("answer", Answers)
    val isCorrect = bool("is_correct")
}