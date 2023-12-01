package dev.timpham.features.quiz.repository

import dev.timpham.data.features.quiz.dao.QuizDAO
import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.models.BaseResponse
import dev.timpham.features.quiz.models.QuizRequest
import io.ktor.http.*
import java.util.UUID

class QuizRepositoryImpl(
    private val quizDao: QuizDAO
): QuizRepository {
    override suspend fun createQuiz(quizRequest: QuizRequest): Pair<HttpStatusCode, BaseResponse<Quiz?>> {
        return quizDao.createQuiz(quizRequest.name, quizRequest.description, quizRequest.isActive)?.let {
            Pair(HttpStatusCode.Created, BaseResponse(data = it))
        } ?: kotlin.run {
            Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "CREATE_QUIZ_FAILED"))
        }
    }

    override suspend fun getQuizById(id: UUID): Pair<HttpStatusCode, BaseResponse<Quiz?>> {
       return quizDao.getQuizById(id)?.let {
              Pair(HttpStatusCode.OK, BaseResponse(data = it))
         } ?: kotlin.run {
              Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_QUIZ"))
       }
    }

    override suspend fun getQuizList(isActive: Boolean?): Pair<HttpStatusCode, BaseResponse<List<Quiz>>> {
        return Pair(HttpStatusCode.OK, BaseResponse(data = quizDao.getQuizList(isActive)))
    }

    override suspend fun updateQuiz(id: UUID, quizRequest: QuizRequest): Pair<HttpStatusCode, BaseResponse<Quiz?>> {
        quizDao.getQuizById(id)
            ?: return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_QUIZ"))
        return quizDao.updateQuiz(id, quizRequest.name, quizRequest.description, quizRequest.isActive,)?.let {
            Pair(HttpStatusCode.OK, BaseResponse(data = it))
        } ?: kotlin.run {
            Pair(HttpStatusCode.OK, BaseResponse(messageCode = "UPDATE_QUIZ_FAILED"))
        }
    }

    override suspend fun deleteQuiz(id: UUID): Pair<HttpStatusCode, BaseResponse<Boolean>> {
        quizDao.getQuizById(id)
            ?: return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_QUIZ"))
        val result = quizDao.deleteQuiz(id)
        return if (result) {
            Pair(HttpStatusCode.OK, BaseResponse(data = true))
        } else {
            Pair(HttpStatusCode.InternalServerError, BaseResponse(messageCode = "DELETE_QUIZ_FAILED"))
        }
    }

}