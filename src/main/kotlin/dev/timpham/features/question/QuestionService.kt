package dev.timpham.features.question

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.common.models.BaseResponse
import dev.timpham.data.features.question.exception.QuestionNotFoundException
import dev.timpham.data.features.question.models.Question
import dev.timpham.data.features.question.models.QuestionRequest
import dev.timpham.data.features.question.repository.QuestionRepository
import dev.timpham.data.features.quiz.exception.QuizNotFoundException
import io.ktor.http.*
import java.util.UUID

class QuestionService(
    private val questionRepository: QuestionRepository
) {
    suspend fun getQuestionById(id: UUID): ResponseAlias<Question?> {
        try {
            questionRepository.getQuestionById(id).let { question ->
                return ResponseAlias(
                    HttpStatusCode.OK,
                    BaseResponse(question)
                )
            }
        }
        catch (e: QuestionNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode = e.message))
        }
    }

    suspend fun getQuestionsByQuizId(quizId: UUID): ResponseAlias<List<Question>> {
        try {
            questionRepository.getQuestionsByQuizId(quizId).let { questions ->
                return ResponseAlias(
                    HttpStatusCode.OK,
                    BaseResponse(questions)
                )
            }
        }
        catch (e: QuizNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode = e.message))
        }
    }

    suspend fun createQuestion(questionRequest: QuestionRequest): ResponseAlias<Question> {
        try {
            questionRepository.createQuestion(questionRequest).let {
                return ResponseAlias(
                    HttpStatusCode.Created,
                    BaseResponse(it)
                )
            }
        }
        catch (e: QuizNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode = e.message))
        }
    }

    suspend fun updateQuestion(
        id: UUID,
        questionRequest: QuestionRequest,
    ): ResponseAlias<Question> {
        try {
            questionRepository.updateQuestion(id, questionRequest).let {
                return ResponseAlias(
                    HttpStatusCode.OK,
                    BaseResponse(it)
                )
            }
        }
        catch (e: QuestionNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode = e.message))
        }
    }

    suspend fun deleteQuestion(id: UUID): ResponseAlias<Boolean> {
        try {
            questionRepository.deleteQuestion(id).let {
                return ResponseAlias(
                    HttpStatusCode.OK,
                    BaseResponse(it)
                )
            }
        }
        catch (e: QuestionNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode = e.message))
        }
    }
}