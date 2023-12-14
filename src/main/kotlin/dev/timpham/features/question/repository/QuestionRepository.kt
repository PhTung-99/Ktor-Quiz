package dev.timpham.features.question.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.data.features.question.models.Question
import dev.timpham.data.features.question.models.QuestionRequest
import java.util.*

interface QuestionRepository {
    suspend fun getQuestionById(id: UUID): ResponseAlias<Question?>
    suspend fun getQuestionsByQuizId(quizId: UUID): ResponseAlias<List<Question>>
    suspend fun createQuestion(question: QuestionRequest): ResponseAlias<Question?>
    suspend fun updateQuestion(id: UUID, question: QuestionRequest): ResponseAlias<Question?>
    suspend fun deleteQuestion(id: UUID): ResponseAlias<Boolean>
}