package dev.timpham.features.question.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.common.models.BaseResponse
import dev.timpham.data.features.question.dao.QuestionDAO
import dev.timpham.data.features.question.models.Question
import dev.timpham.data.features.quiz.dao.QuizDAO
import dev.timpham.features.question.models.QuestionRequest
import io.ktor.http.*
import java.util.UUID

class QuestionRepositoryImpl(
    private val questionDAO: QuestionDAO,
    private val quizDAO: QuizDAO,
): QuestionRepository {
    override suspend fun getQuestionById(id: UUID): ResponseAlias<Question?> {
        return questionDAO.getQuestionById(id)?.let {
            Pair(HttpStatusCode.OK, BaseResponse(data = it))
        } ?: kotlin.run {
            Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_QUIZ"))
        }
    }

    override suspend fun getQuestionsByQuizId(quizId: UUID): ResponseAlias<List<Question>> {
        val quiz = quizDAO.getQuizById(quizId)
        quiz?.let {
            return Pair(HttpStatusCode.OK, BaseResponse(data = questionDAO.getQuestionsByQuizId(quizId)))
        } ?: kotlin.run {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_QUIZ"))
        }
    }

    override suspend fun createQuestion(question: QuestionRequest): ResponseAlias<Question?> {
        questionDAO.createQuestion(question.content, question.highlight, question.quizId)?.let {
            return Pair(HttpStatusCode.Created, BaseResponse(data = it))
        } ?: kotlin.run {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "CREATE_QUIZ_FAILED"))
        }
    }

    override suspend fun updateQuestion(id: UUID, question: QuestionRequest): ResponseAlias<Question?> {
        questionDAO.updateQuestion(id, question.content, question.highlight, question.quizId)?.let {
            return Pair(HttpStatusCode.OK, BaseResponse(data = it))
        } ?: kotlin.run {
            return Pair(HttpStatusCode.OK, BaseResponse(messageCode = "UPDATE_QUIZ_FAILED"))
        }
    }

    override suspend fun deleteQuestion(id: UUID): ResponseAlias<Boolean> {
        questionDAO.getQuestionById(id)
            ?: return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_QUIZ"))
        val result = questionDAO.deleteQuestion(id)
        return if (result) {
            Pair(HttpStatusCode.OK, BaseResponse(data = true))
        } else {
            Pair(HttpStatusCode.InternalServerError, BaseResponse(messageCode = "DELETE_QUIZ_FAILED"))
        }
    }

}