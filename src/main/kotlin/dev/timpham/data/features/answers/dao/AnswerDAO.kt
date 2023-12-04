package dev.timpham.data.features.answers.dao

import dev.timpham.data.features.answers.models.Answer
import java.util.UUID

interface AnswerDAO {
    suspend fun getAnswerById(id: UUID): Answer?
    suspend fun getAnswersByQuestionId(questionId: UUID): List<Answer>?
    suspend fun createAnswer(content: String, isCorrect: Boolean, questionId: UUID): Answer?
    suspend fun updateAnswer(id: UUID, content: String, isCorrect: Boolean, questionId: UUID): Answer?
    suspend fun deleteAnswer(id: UUID): Boolean
}