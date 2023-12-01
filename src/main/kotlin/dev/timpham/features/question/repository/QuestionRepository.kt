package dev.timpham.features.question.repository

import dev.timpham.data.features.question.models.Question
import dev.timpham.features.question.models.QuestionRequest
import io.ktor.http.*
import java.util.UUID

interface QuestionRepository {
    suspend fun getQuestionById(id: UUID): Pair<HttpStatusCode, Question?>
    suspend fun getQuestionsByQuizId(quizId: UUID): Pair<HttpStatusCode,List<Question>>
    suspend fun createQuestion(question: QuestionRequest): Pair<HttpStatusCode,Question?>
    suspend fun updateQuestion(question: Question): Pair<HttpStatusCode,Question?>
    suspend fun deleteQuestion(id: UUID): Pair<HttpStatusCode,Boolean>
}