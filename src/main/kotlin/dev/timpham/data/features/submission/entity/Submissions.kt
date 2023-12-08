package dev.timpham.data.features.submission.entity

import dev.timpham.data.database.BaseTable
import dev.timpham.data.features.question.entity.Questions
import dev.timpham.data.features.user.entity.Users

object Submissions: BaseTable("user_answer_history") {
    val user = reference("user", Users)
    val question = reference("question", Questions)
    val score = integer("is_correct")
}