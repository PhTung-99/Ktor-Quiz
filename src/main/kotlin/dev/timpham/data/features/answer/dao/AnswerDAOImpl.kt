package dev.timpham.data.features.answer.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.answer.entity.AnswerEntity
import dev.timpham.data.features.answer.entity.Answers
import dev.timpham.data.features.answer.mapper.entityToAnswer
import dev.timpham.data.features.answer.models.Answer
import dev.timpham.data.features.answer.models.AnswerRequest
import dev.timpham.data.features.question.entity.QuestionEntity
import java.util.UUID

class AnswerDAOImpl: AnswerDAO {
    override suspend fun getAnswerById(id: UUID): Answer? = dbQuery {
        AnswerEntity.findById(id)?.let(::entityToAnswer)
    }

    override suspend fun getAnswersByQuestionId(questionId: UUID): List<Answer> = dbQuery {
        AnswerEntity.find {
            Answers.question eq questionId
        }.map(::entityToAnswer)
    }

    override suspend fun createAnswer(answerRequest: AnswerRequest): Answer = dbQuery {
        AnswerEntity.new {
            content = answerRequest.content
            isCorrect = answerRequest.isCorrect
            question = QuestionEntity[answerRequest.questionId!!]
        }.let(::entityToAnswer)
    }

    override suspend fun updateAnswer(id: UUID, answerRequest: AnswerRequest): Answer? = dbQuery {
        val answer = AnswerEntity.findById(id)
        answer?.apply {
            content = answerRequest.content
            isCorrect = answerRequest.isCorrect
            question = QuestionEntity[answerRequest.questionId!!]
        }?.let(::entityToAnswer)
    }

    override suspend fun deleteAnswer(id: UUID): Boolean = dbQuery {
        AnswerEntity.findById(id)?.run{
            this.isDeleted = true
            return@dbQuery true
        }?: false
    }
}
