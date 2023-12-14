package dev.timpham.data.features.quiz.dao

import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.features.quiz.models.QuizType
import dev.timpham.data.features.quiz.models.request.QuizRequest
import java.util.*

interface QuizDAO {
    suspend fun createQuiz(
        quizRequest: QuizRequest
    ): Quiz
    suspend fun getQuizById(id: UUID): Quiz?
    suspend fun updateQuiz(id: UUID, quizRequest: QuizRequest): Quiz?
    suspend fun deleteQuiz(id: UUID)
    suspend fun getQuizList(name: String?, type: QuizType?, isActive: Boolean?): List<Quiz>
}