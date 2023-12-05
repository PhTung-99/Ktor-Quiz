package dev.timpham.features.quiz.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.data.features.quiz.dao.QuizDAO
import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.common.models.BaseResponse
import dev.timpham.features.quiz.models.QuizRequest
import io.ktor.http.*
import java.util.UUID

class QuizRepositoryImpl(
    private val quizDao: QuizDAO
): QuizRepository {
    override suspend fun createQuiz(quizRequest: QuizRequest): ResponseAlias<Quiz?> {
        return Pair(
            HttpStatusCode.Created,
            BaseResponse(data = quizDao.createQuiz(quizRequest.name, quizRequest.description, quizRequest.isActive))
        )
    }

    override suspend fun getQuizById(id: UUID): ResponseAlias<Quiz?> {
       return quizDao.getQuizById(id)?.let {
              Pair(HttpStatusCode.OK, BaseResponse(data = it))
         } ?: kotlin.run {
              Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_QUIZ"))
       }
    }

    override suspend fun getQuizList(isActive: Boolean?): ResponseAlias<List<Quiz>> {
        return Pair(HttpStatusCode.OK, BaseResponse(data = quizDao.getQuizList(isActive)))
    }

    override suspend fun updateQuiz(id: UUID, quizRequest: QuizRequest): ResponseAlias<Quiz?> {
        quizDao.getQuizById(id)
            ?: return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_QUIZ"))
        return quizDao.updateQuiz(id, quizRequest.name, quizRequest.description, quizRequest.isActive,)?.let {
            Pair(HttpStatusCode.OK, BaseResponse(data = it))
        } ?: kotlin.run {
            Pair(HttpStatusCode.OK, BaseResponse(messageCode = "UPDATE_QUIZ_FAILED"))
        }
    }

    override suspend fun deleteQuiz(id: UUID): ResponseAlias<Boolean> {
        quizDao.getQuizById(id)
            ?: return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_QUIZ"))
        quizDao.deleteQuiz(id)
        return Pair(HttpStatusCode.OK, BaseResponse(data = true))
    }
}