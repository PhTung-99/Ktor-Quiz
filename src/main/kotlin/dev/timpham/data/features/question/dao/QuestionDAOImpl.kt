package dev.timpham.data.features.question.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.answers.entity.AnswerEntity
import dev.timpham.data.features.answers.entity.Answers
import dev.timpham.data.features.answers.mapper.entityToAnswer
import dev.timpham.data.features.question.entity.QuestionEntity
import dev.timpham.data.features.question.entity.Questions
import dev.timpham.data.features.question.mapper.entityToQuestion
import dev.timpham.data.features.question.models.Question
import dev.timpham.data.features.question.models.QuestionRequest
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

    override suspend fun createQuestion(questionRequest: QuestionRequest): Question = dbQuery {
        QuestionEntity.new {
            content = questionRequest.content
            highlight = questionRequest.highlight
            isMultipleChoice = questionRequest.isMultipleChoice
            score = questionRequest.score
            quiz = QuizEntity[questionRequest.quizId!!]
        }.let(::entityToQuestion)
    }

    override suspend fun updateQuestion(id: UUID, questionRequest: QuestionRequest): Question? = dbQuery {
        QuestionEntity.findById(id)?.apply {
            content = questionRequest.content
            highlight = questionRequest.highlight
            isMultipleChoice = questionRequest.isMultipleChoice
            score = questionRequest.score
            quiz = QuizEntity[questionRequest.quizId!!]
        }?.let(::entityToQuestion)
    }

    override suspend fun deleteQuestion(id: UUID): Boolean = dbQuery {
        QuestionEntity.findById(id)?.let {
            it.delete()
            return@dbQuery it.isDeleted
        } ?: false
    }
}