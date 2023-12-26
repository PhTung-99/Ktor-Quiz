package dev.timpham.data.features.question.repository

import dev.timpham.data.features.question.dao.QuestionDAO
import dev.timpham.data.features.question.exception.QuestionNotFoundException
import dev.timpham.data.features.question.models.Question
import dev.timpham.data.features.question.models.QuestionRequest
import dev.timpham.data.features.quiz.exception.QuizNotFoundException
import java.util.*

class QuestionRepositoryImpl(
    private val questionDAO: QuestionDAO,
): QuestionRepository {
    override suspend fun getQuestionById(id: UUID): Question {
        questionDAO.getQuestionById(id)?.let { question ->
            return question
        } ?: run {
            throw QuestionNotFoundException()
        }
    }

    override suspend fun getQuestionsByQuizId(quizId: UUID): List<Question> {
        val result = questionDAO.getQuestionsByQuizId(quizId)
        if (result.isNotEmpty()) {
            return result
        }
        else {
            throw QuizNotFoundException()
        }
    }

    override suspend fun createQuestion(question: QuestionRequest): Question {
        if (question.quizId == null) {
            throw QuizNotFoundException()
        }
        questionDAO.createQuestion(question).let { newQuestion ->
            return newQuestion
        }
    }

    override suspend fun updateQuestion(id: UUID, question: QuestionRequest): Question {
        if (question.quizId == null) {
            throw QuizNotFoundException()
        }
        questionDAO.updateQuestion(id, question)?.let {
            return it
        } ?: run {
            throw QuestionNotFoundException()
        }
    }

    override suspend fun deleteQuestion(id: UUID): Boolean {
        val result = questionDAO.deleteQuestion(id)
        return if (result) {
            true
        } else {
            throw QuestionNotFoundException()
        }
    }
}