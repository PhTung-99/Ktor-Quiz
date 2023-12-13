package dev.timpham.data.features.userAnswerHistory.entity

import dev.timpham.data.database.BaseTable
import dev.timpham.data.database.customexpression.TimeDifferenceExpression
import dev.timpham.data.database.datetypes.timestampWithTimeZone
import dev.timpham.data.features.quiz.entity.Quizzes
import dev.timpham.data.features.user.entity.Users
import org.jetbrains.exposed.sql.Expression

object UserAnswerHistories: BaseTable("user_answer_history") {
    val user = reference("user", Users)
    val quiz = reference("quiz", Quizzes)
    val score = integer("score")
    val startTime = timestampWithTimeZone("start_time")
    val endTime = timestampWithTimeZone("end_time")

    val duration: Expression<Long>
        get() = TimeDifferenceExpression(endTime, startTime)
}