package dev.timpham.data.features.userAnswerHistory.dao

import java.util.UUID

interface UserAnswerDAO {
    suspend fun submitAnswer(userId: UUID, questionId: UUID, answerId: UUID)
}