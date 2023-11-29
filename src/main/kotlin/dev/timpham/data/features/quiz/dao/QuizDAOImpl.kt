package dev.timpham.data.features.quiz.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.quiz.mapper.resultRowToQuiz
import dev.timpham.data.features.quiz.models.Quiz
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.*

class QuizDAOImpl: QuizDAO {
    override suspend fun createQuiz(name: String, description: String, isActive: Boolean,): Quiz? = dbQuery {
        val createStatement = QuizEntity.insert {
            it[QuizEntity.name] = name
            it[QuizEntity.description] = description
            it[QuizEntity.isActive] = isActive
        }
        createStatement.resultedValues?.singleOrNull()?.let(::resultRowToQuiz)
    }

    override suspend fun getQuizById(id: UUID): Quiz? = dbQuery {
        val quiz = QuizEntity.select {
            QuizEntity.id eq id
        }
        return@dbQuery quiz.singleOrNull()?.let(::resultRowToQuiz)
    }

    override suspend fun updateQuiz(id: UUID, name: String, description: String, isActive: Boolean): Quiz? {
        QuizEntity.update({QuizEntity.id eq id}) {
            it[QuizEntity.name] = name
            it[QuizEntity.description] = description
            it[QuizEntity.isActive] = isActive
        }
        return getQuizById(id)
    }

    override suspend fun deleteQuiz(id: UUID): Boolean {
        return QuizEntity.deleteWhere {
            QuizEntity.id eq id
        } > 0
    }

    override suspend fun getQuizList(): List<Quiz> = dbQuery {
        QuizEntity.selectAll().map(::resultRowToQuiz)
    }
}