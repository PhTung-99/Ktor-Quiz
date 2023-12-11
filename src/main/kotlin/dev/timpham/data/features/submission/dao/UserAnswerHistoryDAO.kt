package dev.timpham.data.features.submission.dao

import dev.timpham.data.features.submission.models.UserAnswer
import java.time.Instant
import java.util.*

interface UserAnswerHistoryDAO {
    suspend fun createSubmission(
        userId: UUID,
        quizId: UUID,
        score: Int,
        startTime: Instant,
        endTime: Instant,
    ): UserAnswer

    suspend fun getSubmissionByUserId(userId: UUID)

    suspend fun getSubmission(id: UUID)
}