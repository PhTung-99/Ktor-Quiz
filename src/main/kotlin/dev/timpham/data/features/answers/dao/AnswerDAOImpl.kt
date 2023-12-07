package dev.timpham.data.features.answers.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.answers.entity.AnswerEntity
import dev.timpham.data.features.answers.entity.Answers
import dev.timpham.data.features.answers.mapper.entityToAnswer
import dev.timpham.data.features.answers.models.Answer
import dev.timpham.data.features.question.entity.QuestionEntity
import java.util.UUID

class AnswerDAOImpl: AnswerDAO {
    override suspend fun getAnswerById(id: UUID): Answer? = dbQuery {
        return@dbQuery AnswerEntity.findById(id)?.let(::entityToAnswer)
    }

    override suspend fun getAnswersByQuestionId(questionId: UUID): List<Answer> = dbQuery{
        return@dbQuery AnswerEntity.find {
            Answers.question eq questionId
        }.map(::entityToAnswer)
    }

    override suspend fun createAnswer(content: String, isCorrect: Boolean, questionId: UUID): Answer = dbQuery {
        AnswerEntity.new {
            this.content = content
            this.isCorrect = isCorrect
            this.question = QuestionEntity[questionId]
        }.let(::entityToAnswer)
    }

    override suspend fun updateAnswer(id: UUID, content: String, isCorrect: Boolean, questionId: UUID): Answer? = dbQuery {
        AnswerEntity.findById(id)?.apply {
            this.content = content
            this.isCorrect = isCorrect
            this.question = QuestionEntity[questionId]
        }?.let(::entityToAnswer)
    }

    override suspend fun deleteAnswer(id: UUID): Boolean = dbQuery {
        AnswerEntity.findById(id)?.let {
            it.delete()
            return@dbQuery it.isDeleted
        } ?: false
    }
}
