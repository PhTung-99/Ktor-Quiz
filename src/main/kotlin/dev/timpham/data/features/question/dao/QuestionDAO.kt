package dev.timpham.data.features.question.dao

import dev.timpham.data.features.question.models.Question
import java.util.UUID

interface QuestionDAO {
    suspend fun getQuestionById(id: UUID): Question?
    suspend fun getQuestionsByQuizId(quizId: UUID): List<Question>
    suspend fun createQuestion(content: String, highlight: String, quizId: UUID): Question?
    suspend fun updateQuestion(id: UUID, content: String, highlight: String, quizId: UUID): Question?
    suspend fun deleteQuestion(id: UUID): Boolean
}