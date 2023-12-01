package dev.timpham.features.quiz.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.features.quiz.models.QuizRequest
import java.util.*

interface QuizRepository {
    suspend fun createQuiz(
        quizRequest: QuizRequest
    ): ResponseAlias<Quiz?>

    suspend fun getQuizById(id: UUID): ResponseAlias<Quiz?>

    suspend fun getQuizList(isActive: Boolean?): ResponseAlias<List<Quiz>>

    suspend fun updateQuiz(id: UUID, quizRequest: QuizRequest): ResponseAlias<Quiz?>

    suspend fun deleteQuiz(id: UUID): ResponseAlias<Boolean>
}