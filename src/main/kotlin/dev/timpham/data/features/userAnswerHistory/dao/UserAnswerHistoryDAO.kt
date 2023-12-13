package dev.timpham.data.features.userAnswerHistory.dao

import dev.timpham.data.features.userAnswerHistory.models.UserAnswerHistory
import java.time.Instant
import java.util.*

interface UserAnswerHistoryDAO {
    suspend fun createSubmission(
        userId: UUID,
        quizId: UUID,
        score: Int,
        startTime: Instant,
        endTime: Instant,
    ): UserAnswerHistory

    suspend fun getSortedUserHistory(quizId: UUID): List<UserAnswerHistory>

    suspend fun getUserHistory(userId: UUID, quizId: UUID): UserAnswerHistory?

}