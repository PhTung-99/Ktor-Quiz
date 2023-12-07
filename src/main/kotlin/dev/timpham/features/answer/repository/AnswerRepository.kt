package dev.timpham.features.answer.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.data.features.answers.models.Answer
import dev.timpham.features.answer.models.AnswerRequest
import java.util.UUID

interface AnswerRepository {
    suspend fun getAnswerById(id: UUID): ResponseAlias<Answer?>
    suspend fun getAnswersByQuestionId(questionId: UUID): ResponseAlias<List<Answer>>
    suspend fun createAnswer(answerRequest: AnswerRequest): ResponseAlias<Answer>
    suspend fun updateAnswer(id: UUID, answerRequest: AnswerRequest): ResponseAlias<Answer?>
    suspend fun deleteAnswer(id: UUID): ResponseAlias<Boolean>
}