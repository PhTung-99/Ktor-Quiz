package dev.timpham.data.features.submission.dao

import java.time.Instant
import java.util.*

interface SubmissionDAO {
    suspend fun createSubmission(
        userId: UUID,
        quizId: UUID,
        score: Int,
        startTime: Instant,
        endTime: Instant,
    )

    suspend fun getSubmissionByUserId(userId: UUID)

    suspend fun getSubmission(id: UUID)
}