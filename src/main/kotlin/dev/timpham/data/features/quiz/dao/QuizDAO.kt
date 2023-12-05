package dev.timpham.data.features.quiz.dao

import dev.timpham.data.features.quiz.models.Quiz
import java.util.UUID

interface QuizDAO {
    suspend fun createQuiz(
        name: String,
        description: String,
        isActive: Boolean,
    ): Quiz
    suspend fun getQuizById(id: UUID): Quiz?
    suspend fun updateQuiz(id: UUID, name: String, description: String, isActive: Boolean): Quiz?
    suspend fun deleteQuiz(id: UUID)
    suspend fun getQuizList(isActive: Boolean?): List<Quiz>
}