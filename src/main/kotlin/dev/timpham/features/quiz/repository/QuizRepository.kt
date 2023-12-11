package dev.timpham.features.quiz.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.features.quiz.models.request.QuizRequest
import dev.timpham.data.features.submission.models.SubmitRequest
import java.util.*

interface QuizRepository {

    suspend fun getQuizById(id: UUID): ResponseAlias<Quiz?>

    suspend fun getQuizList(isActive: Boolean?): ResponseAlias<List<Quiz>>

    suspend fun createQuiz(quizRequest: QuizRequest): ResponseAlias<Quiz>

    suspend fun updateQuiz(id: UUID, quizRequest: QuizRequest): ResponseAlias<Quiz?>

    suspend fun deleteQuiz(id: UUID): ResponseAlias<Boolean>

    suspend fun playQuiz(id: UUID): ResponseAlias<Quiz?>

    suspend fun submitAnswer(quizId: UUID, userId: UUID, submitRequest: SubmitRequest): ResponseAlias<Any>

}