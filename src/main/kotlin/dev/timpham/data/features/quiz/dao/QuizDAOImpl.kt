package dev.timpham.data.features.quiz.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.category.entity.CategoryEntity
import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.quiz.entity.Quizzes
import dev.timpham.data.features.quiz.mapper.entityToQuiz
import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.features.quiz.models.QuizType
import dev.timpham.data.features.quiz.models.request.QuizRequest
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.lowerCase
import org.jetbrains.exposed.sql.selectAll
import java.util.*

class QuizDAOImpl: QuizDAO {
    override suspend fun createQuiz(quizRequest: QuizRequest): Quiz = dbQuery {
        QuizEntity.new {
            name = quizRequest.name
            description = quizRequest.description
            isActive = quizRequest.isActive
            category = CategoryEntity[quizRequest.categoryId]
            type = quizRequest.type
        }.let(::entityToQuiz)
    }

    override suspend fun getQuizById(id: UUID): Quiz? = dbQuery {
        QuizEntity.findById(id)?.let(::entityToQuiz)
    }

    override suspend fun updateQuiz(id: UUID, quizRequest: QuizRequest): Quiz? = dbQuery {
        QuizEntity.findById(id)?.apply {
            name = quizRequest.name
            description = quizRequest.description
            isActive = quizRequest.isActive
            type = quizRequest.type
        }?.let(::entityToQuiz)
    }

    override suspend fun deleteQuiz(id: UUID): Boolean = dbQuery {
        QuizEntity.findById(id)?.run {
            this.isDeleted = true
            return@dbQuery true
        } ?: false
    }

    override suspend fun getQuizList(name: String?, type: QuizType?, isActive: Boolean?): List<Quiz> = dbQuery {
        val query = Quizzes.selectAll()
        name?.let {
            query.andWhere { Quizzes.name.lowerCase() like "%${it.trim().lowercase()}%" }
        }
        type?.let {
            query.andWhere { Quizzes.type eq it }
        }
        isActive?.let {
            query.andWhere { Quizzes.isActive eq it }
        }
        QuizEntity.wrapRows(query).map(::entityToQuiz)
    }
}