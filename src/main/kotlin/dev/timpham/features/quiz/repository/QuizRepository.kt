package dev.timpham.features.quiz.repository

import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.models.BaseResponse
import dev.timpham.features.quiz.models.QuizRequest
import io.ktor.http.*
import java.util.*

interface QuizRepository {
    suspend fun createQuiz(
        quizRequest: QuizRequest
    ): Pair<HttpStatusCode, BaseResponse<Quiz?>>

    suspend fun getQuizById(id: UUID): Pair<HttpStatusCode, BaseResponse<Quiz?>>

    suspend fun getQuizList(isActive: Boolean?): Pair<HttpStatusCode, BaseResponse<List<Quiz>>>

    suspend fun updateQuiz(id: UUID, quizRequest: QuizRequest): Pair<HttpStatusCode, BaseResponse<Quiz?>>

    suspend fun deleteQuiz(id: UUID): Pair<HttpStatusCode, BaseResponse<Boolean>>
}