package dev.timpham.data.features.submission.entity

import dev.timpham.data.database.BaseTable
import dev.timpham.data.database.datetypes.timestampWithTimeZone
import dev.timpham.data.features.quiz.entity.Quizzes
import dev.timpham.data.features.user.entity.Users

object Submissions: BaseTable("user_answer_history") {
    val user = reference("user", Users)
    val quiz = reference("quiz", Quizzes)
    val score = integer("is_correct")
    val startTime = timestampWithTimeZone("start_time")
    val endTime = timestampWithTimeZone("end_time")
}