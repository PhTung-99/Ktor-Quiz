package dev.timpham.data.features.submission.dao

import java.util.*

interface SubmissionDAO {
    suspend fun createSubmission(
        userId: UUID,
        quizId: UUID,
        score: Int
    )

    suspend fun getSubmissionByUserId(userId: UUID)

    suspend fun getSubmission(id: UUID)
}