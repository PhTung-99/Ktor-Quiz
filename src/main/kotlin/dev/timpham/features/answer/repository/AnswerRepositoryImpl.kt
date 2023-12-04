package dev.timpham.features.answer.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.common.models.BaseResponse
import dev.timpham.data.features.answers.dao.AnswerDAO
import dev.timpham.data.features.answers.models.Answer
import io.ktor.http.*
import java.util.*

class AnswerRepositoryImpl(
    private val answerDAO: AnswerDAO
): AnswerRepository {
    override suspend fun getAnswerById(id: UUID): ResponseAlias<Answer?> {
        return answerDAO.getAnswerById(id)?.let {
            return Pair(HttpStatusCode.OK, BaseResponse(it))
        } ?: run {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_ANSWER"))
        }
    }

    override suspend fun createAnswer(content: String, isCorrect: Boolean, questionId: UUID): ResponseAlias<Answer?> {
        answerDAO.createAnswer(content, isCorrect, questionId)?.let {
            return Pair(HttpStatusCode.Created, BaseResponse(it))
        } ?: run {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "CREATE_ANSWER_FAILED"))
        }
    }

    override suspend fun updateAnswer(
        id: UUID,
        content: String,
        isCorrect: Boolean,
        questionId: UUID
    ): ResponseAlias<Answer?> {
        answerDAO.updateAnswer(id, content, isCorrect, questionId)?.let {
            return Pair(HttpStatusCode.OK, BaseResponse(it))
        } ?: run {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "UPDATE_ANSWER_FAILED"))
        }
    }

    override suspend fun deleteAnswer(id: UUID): ResponseAlias<Boolean> {
        answerDAO.getAnswerById(id)
            ?: return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_ANSWER"))
        val result = answerDAO.deleteAnswer(id)
        return if (result) {
            Pair(HttpStatusCode.OK, BaseResponse(data = true))
        } else {
            Pair(HttpStatusCode.InternalServerError, BaseResponse(messageCode = "DELETE_ANSWER_FAILED"))
        }
    }

}