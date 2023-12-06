package dev.timpham.data.features.answers.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.answers.entity.AnswerEntity
import dev.timpham.data.features.answers.entity.Answers
import dev.timpham.data.features.answers.models.Answer
import dev.timpham.data.features.question.entity.QuestionEntity
import java.util.*

class AnswerDaoImpl: AnswerDAO {
    override suspend fun getAnswerById(id: UUID): Answer? = dbQuery {
        AnswerEntity.findById(id)?.toAnswer()
    }

    override suspend fun getAnswersByQuestionId(questionId: UUID): List<Answer> = dbQuery {
        AnswerEntity.find {
            Answers.question eq questionId
        }.map {
            it.toAnswer()
        }
    }

    override suspend fun createAnswer(content: String, isCorrect: Boolean, questionId: UUID): Answer = dbQuery {
        AnswerEntity.new {
            this.content = content
            this.isCorrect = isCorrect
            this.question = QuestionEntity[questionId]
        }.toAnswer()
    }

    override suspend fun updateAnswer(id: UUID, content: String, isCorrect: Boolean, questionId: UUID): Answer? = dbQuery {
        val answer = AnswerEntity.findById(id)
        answer?.apply {
            this.content = content
            this.isCorrect = isCorrect
            this.question = QuestionEntity[questionId]
        }
        answer?.toAnswer()
    }

    override suspend fun deleteAnswer(id: UUID): Boolean = dbQuery {
        AnswerEntity.findById(id)?.let{
            it.delete()
            return@dbQuery  true
        }?: false
    }
}