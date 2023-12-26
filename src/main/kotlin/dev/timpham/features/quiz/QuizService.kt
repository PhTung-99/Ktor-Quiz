package dev.timpham.features.quiz

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.common.models.BaseResponse
import dev.timpham.data.features.quiz.exception.InvalidAnswerRequestException
import dev.timpham.data.features.quiz.exception.QuizNotFoundException
import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.features.quiz.models.QuizType
import dev.timpham.data.features.quiz.models.request.QuizRequest
import dev.timpham.data.features.quiz.repository.QuizRepository
import dev.timpham.data.features.userAnswerHistory.models.SubmitRequest
import dev.timpham.data.features.userAnswerHistory.models.UserAnswerHistory
import io.ktor.http.*
import java.util.UUID

class QuizService(
    private val quizRepository: QuizRepository
) {

    suspend fun createQuiz(quizRequest: QuizRequest): ResponseAlias<Quiz> {
        try {
            quizRepository.createQuiz(quizRequest).let { quiz ->
                return ResponseAlias(HttpStatusCode.Created, BaseResponse(quiz))
            }
        }
        catch (e: InvalidAnswerRequestException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode = e.message))
        }
    }
    suspend fun getQuizById(id: UUID): ResponseAlias<Quiz> {
        try {
            quizRepository.getQuizById(id).let { quiz ->
                return ResponseAlias(HttpStatusCode.OK, BaseResponse(quiz))
            }
        }
        catch (e: QuizNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode = e.message))
        }
    }

    suspend fun getQuizList(name: String?, type: QuizType?, isActive: Boolean?): ResponseAlias<List<Quiz>> {
        quizRepository.getQuizList(name, type, isActive).let { quizList ->
            return ResponseAlias(HttpStatusCode.OK, BaseResponse(quizList))
        }
    }

    suspend fun updateQuiz(id: UUID, quizRequest: QuizRequest): ResponseAlias<Quiz> {
        try {
            quizRepository.updateQuiz(id, quizRequest).let { quiz ->
                return ResponseAlias(HttpStatusCode.OK, BaseResponse(quiz))
            }
        }
        catch (e: QuizNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode = e.message))
        }
    }

    suspend fun deleteQuiz(id: UUID): ResponseAlias<Boolean> {
        try {
            quizRepository.deleteQuiz(id).let { result ->
                return ResponseAlias(HttpStatusCode.OK, BaseResponse(data = result))
            }
        }
        catch (e: QuizNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode = e.message))
        }
    }

    suspend fun submitAnswer(quizId: UUID, userId: UUID, submitRequest: SubmitRequest): ResponseAlias<UserAnswerHistory> {
        quizRepository.submitAnswer(quizId, userId, submitRequest).let { userAnswerHistory ->
            return ResponseAlias(HttpStatusCode.OK, BaseResponse(userAnswerHistory))
        }
    }

    suspend fun getLeaderboard(quizId: UUID): ResponseAlias<List<UserAnswerHistory>> {
        quizRepository.getLeaderboard(quizId).let { userAnswerHistoryList ->
            return ResponseAlias(HttpStatusCode.OK, BaseResponse(userAnswerHistoryList))
        }
    }

    suspend fun getUserScore(quizId: UUID, userId: UUID): ResponseAlias<UserAnswerHistory> {
        quizRepository.getUserScore(quizId, userId).let { userAnswerHistory ->
            return ResponseAlias(HttpStatusCode.OK, BaseResponse(userAnswerHistory))
        }
    }
}