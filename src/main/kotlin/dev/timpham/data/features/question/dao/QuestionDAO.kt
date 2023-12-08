package dev.timpham.data.features.question.dao

import dev.timpham.data.features.question.models.Question
import dev.timpham.data.features.question.models.QuestionRequest
import java.util.UUID

interface QuestionDAO {
    suspend fun getQuestionById(id: UUID): Question?
    suspend fun getQuestionsByQuizId(quizId: UUID): List<Question>
    suspend fun getQuestionWithAnswersById(id: UUID): Question?
    suspend fun createQuestion(questionRequest: QuestionRequest): Question
    suspend fun updateQuestion(id: UUID, questionRequest: QuestionRequest): Question?
    suspend fun deleteQuestion(id: UUID): Boolean
}