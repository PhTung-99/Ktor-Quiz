package dev.timpham.data.features.quiz.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.quiz.mapper.resultRowToQuiz
import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.features.user.entity.UserEntity
import dev.timpham.data.features.user.mapper.resultRowToUserWithPassword
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import java.util.*

class QuizDAOImpl: QuizDAO {
    override suspend fun createQuiz(quiz: Quiz): Quiz? = dbQuery {
        val createStatement = QuizEntity.insert {
            it[name] = quiz.name
            it[description] = quiz.description
        }
        createStatement.resultedValues?.singleOrNull()?.let(::resultRowToQuiz)
    }

    override suspend fun getQuizById(id: UUID): Quiz? = dbQuery {
        val quiz = QuizEntity.select {
            QuizEntity.id eq id
        }
        return@dbQuery quiz.singleOrNull()?.let(::resultRowToQuiz)
    }

    override suspend fun updateQuiz(id: UUID, quiz: Quiz): Quiz {
        val updateStatement = QuizEntity.update({QuizEntity.id eq id}) {
            it[name] = quiz.name
            it[description] = quiz.description
            it[isActive] = quiz.isActive
        }
        return updateStatement.let(::resultRowToQuiz)
    }

    override suspend fun deleteQuiz(id: UUID) {
        TODO("Not yet implemented")
    }

}