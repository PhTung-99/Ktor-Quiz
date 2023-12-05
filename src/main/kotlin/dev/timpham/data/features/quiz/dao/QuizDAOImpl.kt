package dev.timpham.data.features.quiz.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.quiz.entity.Quizzes
import dev.timpham.data.features.quiz.mapper.resultRowToQuiz
import dev.timpham.data.features.quiz.models.Quiz

import java.util.*

class QuizDAOImpl: QuizDAO {
    override suspend fun createQuiz(name: String, description: String, isActive: Boolean,): Quiz = dbQuery {
        val quiz = QuizEntity.new {
            this.name = name
            this.description = description
            this.isActive = isActive
        }
        return@dbQuery quiz.let(::resultRowToQuiz)
    }

    override suspend fun getQuizById(id: UUID): Quiz? = dbQuery {
        QuizEntity.findById(id)?.let(::resultRowToQuiz)
    }

    override suspend fun updateQuiz(id: UUID, name: String, description: String, isActive: Boolean): Quiz? = dbQuery {
        QuizEntity.findById(id)?.apply {
            this.name = name
            this.description = description
            this.isActive = isActive
        }?.let(::resultRowToQuiz)
    }

    override suspend fun deleteQuiz(id: UUID): Unit = dbQuery {
        QuizEntity.findById(id)?.delete()
    }

    override suspend fun getQuizList(isActive: Boolean?): List<Quiz> = dbQuery {
        // selectAll when isActive is null, else not null query with isActive
        if (isActive != null) {
            QuizEntity.find {
                Quizzes.isActive eq isActive
            }.map(::resultRowToQuiz)
        } else {
            QuizEntity
                .all()
                .sortedByDescending { Quizzes.createdAtUTC }
                .map(::resultRowToQuiz)
        }
    }
}