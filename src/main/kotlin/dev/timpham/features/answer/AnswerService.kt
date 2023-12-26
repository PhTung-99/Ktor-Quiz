package dev.timpham.features.answer

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.common.models.BaseResponse
import dev.timpham.data.features.answer.exception.AnswerNotFoundException
import dev.timpham.data.features.answer.models.Answer
import dev.timpham.data.features.answer.models.AnswerRequest
import dev.timpham.data.features.answer.repository.AnswerRepository
import dev.timpham.data.features.question.exception.QuestionNotFoundException
import io.ktor.http.*
import java.util.UUID

class AnswerService(
    private val answerRepository: AnswerRepository
) {
    suspend fun getAnswerById(id: UUID): ResponseAlias<Answer?> {
        try {
            answerRepository.getAnswerById(id).let {
                return ResponseAlias(HttpStatusCode.OK, BaseResponse(it))
            }
        }
        catch (e: AnswerNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode= e.message))
        }
    }

    suspend fun getAnswersByQuestionId(questionId: UUID): ResponseAlias<List<Answer>> {
        try {
            answerRepository.getAnswersByQuestionId(questionId).let {
                return ResponseAlias(HttpStatusCode.OK, BaseResponse(it))
            }
        }
        catch (e: QuestionNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode= e.message))
        }
    }

    suspend fun createAnswer(answerRequest: AnswerRequest): ResponseAlias<Answer> {
        try {
            answerRepository.createAnswer(answerRequest).let {
                return ResponseAlias(HttpStatusCode.Created, BaseResponse(it))
            }
        }
        catch (e: QuestionNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode= e.message))
        }
    }

    suspend fun updateAnswer(id: UUID, answerRequest: AnswerRequest): ResponseAlias<Answer?> {
        try {
            answerRepository.updateAnswer(id, answerRequest).let {
                return ResponseAlias(HttpStatusCode.OK, BaseResponse(it))
            }
        }
        catch (e: QuestionNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode= e.message))
        }
        catch (e: AnswerNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode= e.message))
        }
    }

    suspend fun deleteAnswer(id: UUID): ResponseAlias<Boolean> {
        try {
            answerRepository.deleteAnswer(id).let {
                return ResponseAlias(HttpStatusCode.OK, BaseResponse(data = true))
            }
        }
        catch (e: AnswerNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode= e.message))
        }
    }
}