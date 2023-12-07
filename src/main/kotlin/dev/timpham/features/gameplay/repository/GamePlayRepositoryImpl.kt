package dev.timpham.features.gameplay.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.common.models.BaseResponse
import dev.timpham.data.features.question.dao.QuestionDAO
import dev.timpham.data.features.quiz.dao.QuizDAO
import dev.timpham.data.features.quiz.models.Quiz
import io.ktor.http.*
import java.util.*

class GamePlayRepositoryImpl(
    private val quizDAO: QuizDAO,
    private val questionDAO: QuestionDAO,
): GamePlayRepository {
    override suspend fun getGameplay(quizId: UUID): ResponseAlias<Quiz?> {
        quizDAO.getQuizById(quizId)?.let { quiz ->
            val questionsWithAnswers = questionDAO.getQuestionsByQuizId(quiz.id)
            return Pair(HttpStatusCode.OK, BaseResponse(quiz.copy(questions = questionsWithAnswers)))
        } ?: run {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_QUIZ"))
        }
    }
}