package dev.timpham.features.quiz.repository

import dev.timpham.data.features.quiz.dao.QuizDAO
import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.models.BaseResponse
import io.ktor.http.*
import java.util.UUID

class QuizRepositoryImpl(
    private val quizDao: QuizDAO
): QuizRepository {
    override suspend fun createQuiz(name: String, description: String, isActive: Boolean): Pair<HttpStatusCode, BaseResponse<Quiz?>> {
        return quizDao.createQuiz(name, description, isActive)?.let {
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

    override suspend fun getQuizList(): Pair<HttpStatusCode, BaseResponse<List<Quiz>>> {
        return Pair(HttpStatusCode.OK, BaseResponse(data = quizDao.getQuizList()))
    }

    override suspend fun updateQuiz(id: UUID, name: String, description: String, isActive: Boolean): Pair<HttpStatusCode, BaseResponse<Quiz?>> {
        return quizDao.updateQuiz(id, name, description, isActive)?.let {
            Pair(HttpStatusCode.OK, BaseResponse(data = it))
        } ?: kotlin.run {
            Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "UPDATE_QUIZ_FAILED"))
        }
    }

    override suspend fun deleteQuiz(id: UUID): Pair<HttpStatusCode, BaseResponse<Boolean>> {
        val result = quizDao.deleteQuiz(id)
        return if (result) {
            Pair(HttpStatusCode.OK, BaseResponse(data = true))
        } else {
            Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "DELETE_QUIZ_FAILED"))
        }
    }

}