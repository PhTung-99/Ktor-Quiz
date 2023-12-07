package dev.timpham.data.features.quiz.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.quiz.entity.Quizzes
import dev.timpham.data.features.quiz.mapper.entityToQuiz
import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.features.quiz.models.QuizType

import java.util.*

class QuizDAOImpl: QuizDAO {
    override suspend fun createQuiz(name: String, description: String, isActive: Boolean, type: QuizType): Quiz = dbQuery {
        QuizEntity.new {
            this.name = name
            this.description = description
            this.isActive = isActive
            this.type = type
        }.let(::entityToQuiz)
    }

    override suspend fun getQuizById(id: UUID): Quiz? = dbQuery {
        QuizEntity.findById(id)?.let(::entityToQuiz)
    }

    override suspend fun updateQuiz(id: UUID, name: String, description: String, isActive: Boolean, type: QuizType): Quiz? = dbQuery {
        QuizEntity.findById(id)?.apply {
            this.name = name
            this.description = description
            this.isActive = isActive
            this.type = type
        }?.let(::entityToQuiz)
    }

    override suspend fun deleteQuiz(id: UUID): Boolean = dbQuery {
        QuizEntity.findById(id)?.let {
            it.delete()
            return@dbQuery it.isDeleted
        } ?: false
    }

    override suspend fun getQuizList(isActive: Boolean?): List<Quiz> = dbQuery {
        // selectAll when isActive is null, else not null query with isActive
        if (isActive != null) {
            QuizEntity.find {
                Quizzes.isActive eq isActive
            }.map(::entityToQuiz)
        } else {
            QuizEntity
                .all()
                .sortedByDescending { Quizzes.createdAtUTC }
                .map(::entityToQuiz)
        }
    }
}