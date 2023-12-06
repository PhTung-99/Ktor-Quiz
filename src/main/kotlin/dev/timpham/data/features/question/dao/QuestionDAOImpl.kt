package dev.timpham.data.features.question.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.answers.entity.AnswerEntity
import dev.timpham.data.features.answers.entity.Answers
import dev.timpham.data.features.question.entity.QuestionEntity
import dev.timpham.data.features.question.entity.Questions
import dev.timpham.data.features.question.models.Question
import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.quiz.entity.Quizzes
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.*

class QuestionDAOImpl: QuestionDAO {
    override suspend fun getQuestionById(id: UUID): Question? = dbQuery {
        QuestionEntity.findById(id)?.toQuestion()
    }

    override suspend fun getQuestionsByQuizId(quizId: UUID): List<Question> = dbQuery {
        QuestionEntity.find{
            Questions.quiz eq quizId
        }.map{
            it.toQuestion()
        }
    }

    override suspend fun getQuestionWithAnswersById(id: UUID): Question? = dbQuery {
        QuestionEntity.findById(id)?.let { question ->
            val answers = AnswerEntity.find {
                Answers.question eq question.id
            }.map { answer ->
                answer.toAnswer()
            }
            question.toQuestion().copy(answers = answers)
        } ?: return@dbQuery null
    }

    override suspend fun createQuestion(content: String, highlight: String?, score: Int, quizId: UUID): Question = dbQuery {
        QuestionEntity.new {
            this.content = content
            this.highlight = highlight
            this.score = score
            this.quiz = QuizEntity[quizId]
        }.toQuestion()
    }

    override suspend fun updateQuestion(id: UUID, content: String, highlight: String?, score: Int, quizId: UUID): Question? = dbQuery {
        QuestionEntity.findById(id)?.apply {
            this.content = content
            this.highlight = highlight
            this.score = score
            this.quiz = QuizEntity[quizId]
        }?.toQuestion()
    }

    override suspend fun deleteQuestion(id: UUID): Boolean = dbQuery {
        Questions.deleteWhere {
            Quizzes.id eq id
        } > 0
    }
}