package dev.timpham.features.quiz.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.common.models.BaseResponse
import dev.timpham.data.features.answers.dao.AnswerDAO
import dev.timpham.data.features.question.dao.QuestionDAO
import dev.timpham.data.features.quiz.dao.QuizDAO
import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.features.quiz.models.QuizType
import dev.timpham.data.features.quiz.models.request.QuizRequest
import dev.timpham.data.features.userAnswerHistory.dao.UserAnswerHistoryDAO
import dev.timpham.data.features.userAnswerHistory.models.SubmitRequest
import dev.timpham.data.features.userAnswerHistory.models.UserAnswerHistory
import io.ktor.http.*
import java.util.*

class QuizRepositoryImpl(
    private val quizDao: QuizDAO,
    private val questionDAO: QuestionDAO,
    private val answerDAO: AnswerDAO,
    private val userAnswerHistoryDAO: UserAnswerHistoryDAO
) : QuizRepository {

    override suspend fun createQuiz(quizRequest: QuizRequest): ResponseAlias<Quiz> {
        if (!validateAnswerForm(quizRequest)) {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "INVALID_ANSWER"))
        }
        quizDao.createQuiz(quizRequest).let { quiz ->
            val questions = quizRequest.questions?.map { questionRequest ->
                questionDAO.createQuestion(
                    questionRequest.copy(quizId = quiz.id)
                ).let { question ->
                    val answer = questionRequest.answers?.map { answerRequest ->
                        answerDAO.createAnswer(answerRequest.copy(questionId = question.id))
                    }
                    question.copy(answers = answer)
                }
            }
            return Pair(HttpStatusCode.Created, BaseResponse(data = quiz.copy(questions = questions)))
        }
    }

    override suspend fun getQuizById(id: UUID): ResponseAlias<Quiz?> {
        return quizDao.getQuizById(id)?.let {
            Pair(HttpStatusCode.OK, BaseResponse(data = it))
        } ?: kotlin.run {
            Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_QUIZ"))
        }
    }

    override suspend fun getQuizList(name: String?, type: QuizType?, isActive: Boolean?): ResponseAlias<List<Quiz>> {
        return Pair(
            HttpStatusCode.OK,
            BaseResponse(
                data = quizDao.getQuizList(
                    name = name,
                    type = type,
                    isActive = isActive
                ),
            ),
        )
    }

    override suspend fun updateQuiz(id: UUID, quizRequest: QuizRequest): ResponseAlias<Quiz?> {
        quizDao.getQuizById(id)
            ?: return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_QUIZ"))
        return quizDao.updateQuiz(id, quizRequest)?.let {
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

    override suspend fun playQuiz(id: UUID): ResponseAlias<Quiz?> {
        quizDao.getQuizById(id)?.let { quiz ->
            return Pair(HttpStatusCode.OK, BaseResponse(data = quiz))
        } ?: run {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_QUIZ"))
        }
    }


    private fun validateAnswerForm(quiz: QuizRequest): Boolean {
        var isValidate = true
        quiz.questions?.forEach { question ->
            question.answers?.let {
                if (question.isMultipleChoice) {
                    if (question.answers.filter { it.isCorrect }.size < 2) {
                        isValidate = false
                    }
                } else {
                    if (question.answers.filter { it.isCorrect }.size != 1) {
                        isValidate = false
                    }
                }
            }
        }
        return isValidate
    }

    override suspend fun submitAnswer(
        quizId: UUID,
        userId: UUID,
        submitRequest: SubmitRequest
    ): ResponseAlias<UserAnswerHistory?> {
        var score = 0
        submitRequest.userAnswers.forEach { userAnswer ->
            questionDAO.getQuestionById(userAnswer.questionId)?.let { question ->
                question.answers?.filter { answer -> answer.isCorrect }?.let { correctAnswers ->
                    if (correctAnswers.map { it.id }.containsAll(userAnswer.answerIds)) {
                        score += question.score
                    }
                }
            }
        }
        userAnswerHistoryDAO.createSubmission(
            userId,
            quizId,
            score,
            submitRequest.startTime,
            submitRequest.endTime,
        ).let {
            return Pair(HttpStatusCode.OK, BaseResponse(data = it))
        }
    }

    override suspend fun getLeaderboard(quizId: UUID): ResponseAlias<List<UserAnswerHistory>> {
        return Pair(HttpStatusCode.OK, BaseResponse(data = userAnswerHistoryDAO.getSortedUserHistory(quizId)))
    }

    override suspend fun getUserScore(quizId: UUID, userId: UUID): ResponseAlias<UserAnswerHistory?> {
        userAnswerHistoryDAO.getUserHistory(userId, quizId)?.let {
            return Pair(HttpStatusCode.OK, BaseResponse(data = it))
        } ?: run {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_USER_HISTORY"))
        }
    }
}