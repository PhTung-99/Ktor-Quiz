package dev.timpham.data.features.answers.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.answers.entity.AnswerTable
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
        AnswerTable.select {
            AnswerTable.id eq id
        }.singleOrNull()?.let(::resultRowToAnswer)
    }

    override suspend fun getAnswersByQuestionId(questionId: UUID): List<Answer> = dbQuery {
        AnswerTable.select {
            AnswerTable.question eq questionId
        }.mapNotNull(::resultRowToAnswer)
    }

    override suspend fun createAnswer(content: String, isCorrect: Boolean, questionId: UUID): Answer? = dbQuery {
        val createStatement = AnswerTable.insert { it ->
            it[AnswerTable.content] = content
            it[AnswerTable.isCorrect] = isCorrect
            it[question] = questionId
        }
        createStatement.resultedValues?.singleOrNull()?.let(::resultRowToAnswer)
    }

    override suspend fun updateAnswer(id: UUID, content: String, isCorrect: Boolean, questionId: UUID): Answer? =
        dbQuery {
            AnswerTable.update({ AnswerTable.id eq id }) {
                it[AnswerTable.content] = content
                it[AnswerTable.isCorrect] = isCorrect
                it[question] = questionId
            }
            getAnswerById(id)
        }

    override suspend fun deleteAnswer(id: UUID): Boolean = dbQuery {
        AnswerTable.deleteWhere {
            AnswerTable.id eq id
        } > 0
    }
}