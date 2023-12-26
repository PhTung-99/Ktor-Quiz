package dev.timpham.data.features.quiz.repository

import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.features.quiz.models.QuizType
import dev.timpham.data.features.quiz.models.request.QuizRequest
import dev.timpham.data.features.userAnswerHistory.models.SubmitRequest
import dev.timpham.data.features.userAnswerHistory.models.UserAnswerHistory
import java.util.*

interface QuizRepository {

    suspend fun getQuizById(id: UUID): Quiz

    suspend fun getQuizList(name: String?, type: QuizType?, isActive: Boolean?): List<Quiz>

    suspend fun createQuiz(quizRequest: QuizRequest): Quiz

    suspend fun updateQuiz(id: UUID, quizRequest: QuizRequest): Quiz

    suspend fun deleteQuiz(id: UUID): Boolean

    suspend fun submitAnswer(quizId: UUID, userId: UUID, submitRequest: SubmitRequest): UserAnswerHistory

    suspend fun getLeaderboard(quizId: UUID): List<UserAnswerHistory>

    suspend fun getUserScore(quizId: UUID, userId: UUID): UserAnswerHistory

}