package dev.timpham.data.features.question.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.answers.entity.AnswerEntity
import dev.timpham.data.features.answers.entity.Answers
import dev.timpham.data.features.answers.mapper.entityToAnswer
import dev.timpham.data.features.question.entity.QuestionEntity
import dev.timpham.data.features.question.entity.Questions
import dev.timpham.data.features.question.mapper.entityToQuestion
import dev.timpham.data.features.question.models.Question
import dev.timpham.data.features.quiz.entity.QuizEntity
import java.util.*

class QuestionDAOImpl: QuestionDAO {
    override suspend fun getQuestionById(id: UUID): Question? = dbQuery {
        QuestionEntity.findById(id)?.let(::entityToQuestion)
    }

    override suspend fun getQuestionsByQuizId(quizId: UUID): List<Question> = dbQuery {
        QuestionEntity.find{
            Questions.quiz eq quizId
        }.map { question ->
            val answers = AnswerEntity.find {
                Answers.question eq question.id.value
            }.map(::entityToAnswer)
            return@map entityToQuestion(question).copy(answers = answers)
        }
    }

    override suspend fun getQuestionWithAnswersById(id: UUID): Question? = dbQuery {
        QuestionEntity.findById(id)?.let { question ->
            val answers = AnswerEntity.find {
                Answers.question eq id
            }.map(::entityToAnswer)
            return@dbQuery entityToQuestion(question).copy(answers = answers)
        } ?: run {
            return@dbQuery null
        }
    }

    override suspend fun createQuestion(content: String, highlight: String?, isMultipleChoice: Boolean, score: Int, quizId: UUID): Question = dbQuery {
        QuestionEntity.new {
            this.content = content
            this.highlight = highlight
            this.isMultipleChoice = isMultipleChoice
            this.score = score
            this.quiz = QuizEntity[quizId]
        }.let(::entityToQuestion)
    }

    override suspend fun updateQuestion(id: UUID, content: String, highlight: String?, isMultipleChoice: Boolean, score: Int, quizId: UUID): Question? = dbQuery {
        QuestionEntity.findById(id)?.apply {
            this.content = content
            this.highlight = highlight
            this.isMultipleChoice = isMultipleChoice
            this.score = score
            this.quiz = QuizEntity[quizId]
        }?.let(::entityToQuestion)
    }

    override suspend fun deleteQuestion(id: UUID): Boolean = dbQuery {
        QuestionEntity.findById(id)?.let {
            it.delete()
            return@dbQuery it.isDeleted
        } ?: false
    }
}