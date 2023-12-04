package dev.timpham.features.answer.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.data.features.answers.models.Answer
import java.util.UUID

interface AnswerRepository {
    suspend fun getAnswerById(id: UUID): ResponseAlias<Answer?>
    suspend fun getAnswersByQuestionId(questionId: UUID): ResponseAlias<List<Answer>>
    suspend fun createAnswer(content: String, isCorrect: Boolean, questionId: UUID): ResponseAlias<Answer?>
    suspend fun updateAnswer(id: UUID, content: String, isCorrect: Boolean, questionId: UUID): ResponseAlias<Answer?>
    suspend fun deleteAnswer(id: UUID): ResponseAlias<Boolean>
}