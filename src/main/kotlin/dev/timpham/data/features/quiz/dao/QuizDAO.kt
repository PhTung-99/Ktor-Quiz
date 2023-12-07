package dev.timpham.data.features.quiz.dao

import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.features.quiz.models.QuizType
import java.util.UUID

interface QuizDAO {
    suspend fun createQuiz(
        name: String,
        description: String,
        isActive: Boolean,
        type: QuizType,
    ): Quiz
    suspend fun getQuizById(id: UUID): Quiz?
    suspend fun updateQuiz(id: UUID, name: String, description: String, isActive: Boolean, type: QuizType): Quiz?
    suspend fun deleteQuiz(id: UUID): Boolean
    suspend fun getQuizList(isActive: Boolean?): List<Quiz>
}