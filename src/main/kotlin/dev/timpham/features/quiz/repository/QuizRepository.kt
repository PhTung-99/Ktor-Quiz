package dev.timpham.features.quiz.repository

import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.models.BaseResponse
import io.ktor.http.*
import java.util.*

interface QuizRepository {
    suspend fun createQuiz(
        name: String,
        description: String,
        isActive: Boolean,
    ): Pair<HttpStatusCode, BaseResponse<Quiz?>>

    suspend fun getQuizById(id: UUID): Pair<HttpStatusCode, BaseResponse<Quiz?>>

    suspend fun getQuizList(): Pair<HttpStatusCode, BaseResponse<List<Quiz>>>

    suspend fun updateQuiz(id: UUID, name: String, description: String, isActive: Boolean): Pair<HttpStatusCode, BaseResponse<Quiz?>>

    suspend fun deleteQuiz(id: UUID): Pair<HttpStatusCode, BaseResponse<Boolean>>
}