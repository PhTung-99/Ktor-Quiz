package dev.timpham.data.features.question.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.answers.entity.AnswerTable
import dev.timpham.data.features.question.entity.QuestionTable
import dev.timpham.data.features.question.mapper.resultRowToQuestion
import dev.timpham.data.features.question.models.Question
import dev.timpham.data.features.quiz.entity.Quizzes
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.*

class QuestionDAOImpl: QuestionDAO {
    override suspend fun getQuestionById(id: UUID): Question? = dbQuery {
        QuestionTable.select {
            QuestionTable.id eq id
        }.singleOrNull()?.let(::resultRowToQuestion)
    }

    override suspend fun getQuestionsByQuizId(quizId: UUID): List<Question> = dbQuery {
        QuestionTable.select {
            QuestionTable.quiz eq quizId
        }.mapNotNull(::resultRowToQuestion)
    }

    override suspend fun getQuestionWithAnswersById(id: UUID): Question? = dbQuery {
        QuestionTable
            .join(AnswerTable, JoinType.RIGHT, QuestionTable.id, AnswerTable.question)
            .select {
                QuestionTable.id eq id
        }
            .singleOrNull()?.let(::resultRowToQuestion)
    }

    override suspend fun createQuestion(content: String, highlight: String?, score: Int, quizId: UUID): Question? = dbQuery {
        val createStatement = QuestionTable.insert {
            it[QuestionTable.content] = content
            it[QuestionTable.highlight] = highlight
            it[QuestionTable.score] = score
            it[quiz] = quizId
        }
        createStatement.resultedValues?.singleOrNull()?.let(::resultRowToQuestion)
    }

    override suspend fun updateQuestion(id: UUID, content: String, highlight: String?, quizId: UUID): Question? = dbQuery {
        QuestionTable.update({QuestionTable.id eq id}) {
            it[QuestionTable.content] = content
            it[QuestionTable.highlight] = highlight
            it[quiz] = quizId
        }
        getQuestionById(id)
    }

    override suspend fun deleteQuestion(id: UUID): Boolean = dbQuery {
        QuestionTable.deleteWhere {
            Quizzes.id eq id
        } > 0
    }
}