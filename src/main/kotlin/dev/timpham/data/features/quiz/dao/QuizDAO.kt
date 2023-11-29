package dev.timpham.data.features.quiz.dao

import dev.timpham.data.features.quiz.models.Quiz
import java.util.UUID

interface QuizDAO {
    suspend fun createQuiz(quiz: Quiz): Quiz?
    suspend fun getQuizById(id: UUID): Quiz?
    suspend fun updateQuiz(id: UUID, quiz: Quiz): Quiz?
    suspend fun deleteQuiz(id: UUID)
}