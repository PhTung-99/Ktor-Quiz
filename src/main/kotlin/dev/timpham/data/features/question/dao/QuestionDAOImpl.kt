package dev.timpham.data.features.question.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.question.entity.QuestionEntity
import dev.timpham.data.features.question.mapper.resultRowToQuestion
import dev.timpham.data.features.question.models.Question
import dev.timpham.data.features.quiz.entity.QuizEntity
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.*

class QuestionDAOImpl: QuestionDAO {
    override suspend fun getQuestionById(id: UUID): Question? = dbQuery {
        QuestionEntity.select {
            QuestionEntity.id eq id
        }.singleOrNull()?.let(::resultRowToQuestion)
    }

    override suspend fun getQuestionsByQuizId(quizId: UUID): List<Question> = dbQuery {
        QuestionEntity.select {
            QuestionEntity.quizId eq quizId
        }.mapNotNull(::resultRowToQuestion)
    }

    override suspend fun createQuestion(content: String, highlight: String?, score: Int, quizId: UUID): Question? = dbQuery {
        val createStatement = QuestionEntity.insert {
            it[QuestionEntity.content] = content
            it[QuestionEntity.highlight] = highlight
            it[QuestionEntity.score] = score
            it[QuestionEntity.quizId] = quizId
        }
        createStatement.resultedValues?.singleOrNull()?.let(::resultRowToQuestion)
    }

    override suspend fun updateQuestion(id: UUID, content: String, highlight: String?, quizId: UUID): Question? = dbQuery {
        QuestionEntity.update({QuestionEntity.id eq id}) {
            it[QuestionEntity.content] = content
            it[QuestionEntity.highlight] = highlight
            it[QuestionEntity.quizId] = quizId
        }
        getQuestionById(id)
    }

    override suspend fun deleteQuestion(id: UUID): Boolean = dbQuery {
        QuestionEntity.deleteWhere {
            QuizEntity.id eq id
        } > 0
    }
}