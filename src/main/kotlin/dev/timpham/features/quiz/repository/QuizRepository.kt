package dev.timpham.features.quiz.repository

import dev.timpham.data.features.quiz.models.Quiz
import java.util.*

interface QuizRepository {
    suspend fun createQuiz(
        name: String,
        description: String,
        isActive: Boolean,
    ): Quiz?

    suspend fun getQuizById(id: UUID): Quiz?

    suspend fun getQuizList(): List<Quiz>

    suspend fun updateQuiz(id: UUID, name: String, description: String, isActive: Boolean): Quiz?

    suspend fun deleteQuiz(id: UUID): Boolean
}