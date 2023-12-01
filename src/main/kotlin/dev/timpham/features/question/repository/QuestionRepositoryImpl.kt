package dev.timpham.features.question.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.data.features.question.dao.QuestionDAO
import dev.timpham.data.features.question.models.Question
import dev.timpham.features.question.models.QuestionRequest
import java.util.UUID

class QuestionRepositoryImpl(
    private val questionDAO: QuestionDAO
): QuestionRepository {
    override suspend fun getQuestionById(id: UUID): ResponseAlias<Question?> {
        TODO("Not yet implemented")
    }

    override suspend fun getQuestionsByQuizId(quizId: UUID): ResponseAlias<List<Question>> {
        TODO("Not yet implemented")
    }

    override suspend fun createQuestion(question: QuestionRequest): ResponseAlias<Question?> {
        TODO("Not yet implemented")
    }

    override suspend fun updateQuestion(id: UUID, question: QuestionRequest): ResponseAlias<Question?> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteQuestion(id: UUID): ResponseAlias<Boolean> {
        TODO("Not yet implemented")
    }

}