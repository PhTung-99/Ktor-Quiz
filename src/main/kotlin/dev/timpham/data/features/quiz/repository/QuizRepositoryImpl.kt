package dev.timpham.data.features.quiz.repository

import dev.timpham.data.features.answer.dao.AnswerDAO
import dev.timpham.data.features.question.dao.QuestionDAO
import dev.timpham.data.features.quiz.dao.QuizDAO
import dev.timpham.data.features.quiz.exception.InvalidAnswerRequestException
import dev.timpham.data.features.quiz.exception.QuizNotFoundException
import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.features.quiz.models.QuizType
import dev.timpham.data.features.quiz.models.request.QuizRequest
import dev.timpham.data.features.userAnswerHistory.dao.UserAnswerHistoryDAO
import dev.timpham.data.features.userAnswerHistory.models.SubmitRequest
import dev.timpham.data.features.userAnswerHistory.models.UserAnswerHistory
import java.util.*

class QuizRepositoryImpl(
    private val quizDao: QuizDAO,
    private val questionDAO: QuestionDAO,
    private val answerDAO: AnswerDAO,
    private val userAnswerHistoryDAO: UserAnswerHistoryDAO
) : QuizRepository {

    override suspend fun createQuiz(quizRequest: QuizRequest): Quiz {
        if (!validateAnswerForm(quizRequest)) {
            throw InvalidAnswerRequestException()
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
            return quiz.copy(questions = questions)
        }
    }

    override suspend fun getQuizById(id: UUID): Quiz {
        quizDao.getQuizById(id)?.let {
            return it
        } ?: run {
            throw QuizNotFoundException()
        }
    }

    override suspend fun getQuizList(name: String?, type: QuizType?, isActive: Boolean?): List<Quiz> {
        return quizDao.getQuizList(name, type, isActive)
    }

    override suspend fun updateQuiz(id: UUID, quizRequest: QuizRequest): Quiz {
        quizDao.updateQuiz(id, quizRequest)?.let { quiz ->
            return quiz
        } ?: run {
            throw QuizNotFoundException()
        }
    }

    override suspend fun deleteQuiz(id: UUID): Boolean {
        val result = quizDao.deleteQuiz(id)
        if (result) {
            return true
        }
        else {
            throw QuizNotFoundException()
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
    ): UserAnswerHistory {
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
            return it
        }
    }

    override suspend fun getLeaderboard(quizId: UUID): List<UserAnswerHistory> {
        return userAnswerHistoryDAO.getSortedUserHistory(quizId)
    }

    override suspend fun getUserScore(quizId: UUID, userId: UUID): UserAnswerHistory {
        userAnswerHistoryDAO.getUserHistory(userId, quizId)?.let {
            return it
        } ?: run {
            throw QuizNotFoundException()
        }
    }
}