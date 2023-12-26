package dev.timpham.data.features.answer.repository

import dev.timpham.data.features.answer.dao.AnswerDAO
import dev.timpham.data.features.answer.exception.AnswerNotFoundException
import dev.timpham.data.features.answer.models.Answer
import dev.timpham.data.features.answer.models.AnswerRequest
import dev.timpham.data.features.question.exception.QuestionNotFoundException
import java.util.UUID

class AnswerRepositoryImpl(
    private val answerDAO: AnswerDAO
): AnswerRepository {
    override suspend fun getAnswerById(id: UUID): Answer {
        return answerDAO.getAnswerById(id)?.let {
            return it
        } ?: run {
            throw AnswerNotFoundException()
        }
    }

    override suspend fun getAnswersByQuestionId(questionId: UUID): List<Answer> {
        val result = answerDAO.getAnswersByQuestionId(questionId)
        if (result.isNotEmpty()) {
            return result
        }
        else {
            throw QuestionNotFoundException()
        }
    }

    override suspend fun createAnswer(answerRequest: AnswerRequest): Answer {
        if (answerRequest.questionId == null) {
            throw QuestionNotFoundException()
        }
        return answerDAO.createAnswer(answerRequest)
    }

    override suspend fun updateAnswer(
        id: UUID,
        answerRequest: AnswerRequest,
    ): Answer {
        if (answerRequest.questionId == null) {
            throw QuestionNotFoundException()
        }
        return answerDAO.updateAnswer(id, answerRequest) ?: throw AnswerNotFoundException()
    }

    override suspend fun deleteAnswer(id: UUID): Boolean {
        val result = answerDAO.deleteAnswer(id)
        return if (result) {
            true
        } else {
            throw AnswerNotFoundException()
        }
    }

}