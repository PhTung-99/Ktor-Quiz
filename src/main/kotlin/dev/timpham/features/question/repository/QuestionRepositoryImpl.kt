package dev.timpham.features.question.repository

import dev.timpham.data.features.question.dao.QuestionDAO
import dev.timpham.data.features.question.models.Question
import dev.timpham.features.question.models.QuestionRequest
import io.ktor.http.*
import java.util.UUID

class QuestionRepositoryImpl(
    private val questionDAO: QuestionDAO
): QuestionRepository {
    override suspend fun getQuestionById(id: UUID): Pair<HttpStatusCode, Question?> {
        TODO()
    }

    override suspend fun getQuestionsByQuizId(quizId: UUID): Pair<HttpStatusCode, List<Question>> {
        TODO("Not yet implemented")
    }

    override suspend fun createQuestion(question: QuestionRequest): Pair<HttpStatusCode, Question?> {
        TODO("Not yet implemented")
    }

    override suspend fun updateQuestion(question: Question): Pair<HttpStatusCode, Question?> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteQuestion(id: UUID): Pair<HttpStatusCode, Boolean> {
        TODO("Not yet implemented")
    }

}