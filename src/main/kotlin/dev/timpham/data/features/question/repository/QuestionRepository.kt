package dev.timpham.data.features.question.repository

import dev.timpham.data.features.question.models.Question
import dev.timpham.data.features.question.models.QuestionRequest
import java.util.*

interface QuestionRepository {
    suspend fun getQuestionById(id: UUID): Question
    suspend fun getQuestionsByQuizId(quizId: UUID): List<Question>
    suspend fun createQuestion(question: QuestionRequest): Question
    suspend fun updateQuestion(id: UUID, question: QuestionRequest): Question
    suspend fun deleteQuestion(id: UUID): Boolean
}