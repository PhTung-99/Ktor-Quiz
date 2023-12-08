package dev.timpham.features.quiz.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.data.features.quiz.dao.QuizDAO
import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.common.models.BaseResponse
import dev.timpham.data.features.answers.dao.AnswerDAO
import dev.timpham.data.features.question.dao.QuestionDAO
import dev.timpham.features.quiz.models.QuizFullForm
import dev.timpham.features.quiz.models.QuizRequest
import io.ktor.http.*
import java.util.UUID

class QuizRepositoryImpl(
    private val quizDao: QuizDAO,
    private val questionDAO: QuestionDAO,
    private val answerDAO: AnswerDAO,
): QuizRepository {
    override suspend fun createQuiz(quizRequest: QuizRequest): ResponseAlias<Quiz?> {
        return Pair(
            HttpStatusCode.Created,
            BaseResponse(data = quizDao.createQuiz(quizRequest.name, quizRequest.description, quizRequest.isActive, quizRequest.type,))
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
        return quizDao.updateQuiz(id, quizRequest.name, quizRequest.description, quizRequest.isActive, quizRequest.type,)?.let {
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
            val questions = questionDAO.getQuestionsByQuizId(quiz.id).map { question ->
                val answers = answerDAO.getAnswersByQuestionId(question.id)
                question.copy(answers = answers)
            }
            return Pair(HttpStatusCode.OK, BaseResponse(data = quiz.copy(questions = questions)))
        } ?: run {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_QUIZ"))
        }
    }

    override suspend fun createQuizFullForm(quizRequest: QuizFullForm): ResponseAlias<Quiz?> {
        if (!validateAnswer(quizRequest)) {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "INVALID_ANSWER"))
        }
        quizDao.createQuiz(quizRequest.name, quizRequest.description, quizRequest.isActive, quizRequest.type).let { quiz ->
            val questions = quizRequest.questions.map { questionRequest ->
                questionDAO.createQuestion(
                    content = questionRequest.content,
                    highlight = questionRequest.highlight,
                    isMultipleChoice = questionRequest.isMultipleChoice,
                    score = questionRequest.score,
                    quizId = quiz.id,
                ).let { question ->
                    val answer = questionRequest.answers.map { answerRequest ->
                        answerDAO.createAnswer(answerRequest.content, answerRequest.isCorrect, question.id)
                    }
                    question.copy(answers = answer)
                }
            }
            return Pair(HttpStatusCode.Created, BaseResponse(data =  quiz.copy(questions = questions)))
        }
    }

    private fun validateAnswer(quiz: QuizFullForm): Boolean {
        var isValidate = true
        quiz.questions.forEach { question ->
            if (question.isMultipleChoice) {
                if (question.answers.filter { it.isCorrect }.size < 2) {
                    isValidate = false
                }
            }
            else {
                if (question.answers.filter { it.isCorrect }.size != 1) {
                    isValidate = false
                }
            }
        }
        return isValidate
    }
}