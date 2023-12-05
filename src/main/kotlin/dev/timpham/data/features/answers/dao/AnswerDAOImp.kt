package dev.timpham.data.features.answers.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.answers.entity.AnswerEntity
import dev.timpham.data.features.answers.mapper.resultRowToAnswer
import dev.timpham.data.features.answers.models.Answer
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import java.util.UUID

class AnswerDAOImp : AnswerDAO {
    override suspend fun getAnswerById(id: UUID): Answer? = dbQuery {
        AnswerEntity.select {
            AnswerEntity.id eq id
        }.singleOrNull()?.let(::resultRowToAnswer)
    }

    override suspend fun getAnswersByQuestionId(questionId: UUID): List<Answer> = dbQuery {
        AnswerEntity.select {
            AnswerEntity.question eq questionId
        }.mapNotNull(::resultRowToAnswer)
    }

    override suspend fun createAnswer(content: String, isCorrect: Boolean, questionId: UUID): Answer? = dbQuery {
        val createStatement = AnswerEntity.insert { it ->
            it[AnswerEntity.content] = content
            it[AnswerEntity.isCorrect] = isCorrect
            it[question] = questionId
        }
        createStatement.resultedValues?.singleOrNull()?.let(::resultRowToAnswer)
    }

    override suspend fun updateAnswer(id: UUID, content: String, isCorrect: Boolean, questionId: UUID): Answer? =
        dbQuery {
            AnswerEntity.update({ AnswerEntity.id eq id }) {
                it[AnswerEntity.content] = content
                it[AnswerEntity.isCorrect] = isCorrect
                it[question] = questionId
            }
            getAnswerById(id)
        }

    override suspend fun deleteAnswer(id: UUID): Boolean = dbQuery {
        AnswerEntity.deleteWhere {
            AnswerEntity.id eq id
        } > 0
    }
}