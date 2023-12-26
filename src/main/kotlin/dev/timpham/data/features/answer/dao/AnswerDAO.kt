package dev.timpham.data.features.answer.dao

import dev.timpham.data.features.answer.models.Answer
import dev.timpham.data.features.answer.models.AnswerRequest
import java.util.UUID

interface AnswerDAO {
    suspend fun getAnswerById(id: UUID): Answer?
    suspend fun getAnswersByQuestionId(questionId: UUID): List<Answer>
    suspend fun createAnswer(answerRequest: AnswerRequest): Answer
    suspend fun updateAnswer(id: UUID, answerRequest: AnswerRequest): Answer?
    suspend fun deleteAnswer(id: UUID): Boolean
}