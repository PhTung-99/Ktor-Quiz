package dev.timpham.features.submission.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.common.models.BaseResponse
import dev.timpham.data.features.question.dao.QuestionDAO
import dev.timpham.data.features.submission.dao.SubmissionDAO
import dev.timpham.data.features.submission.models.SubmitRequest
import io.ktor.http.*
import java.util.UUID

class SubmissionRepositoryImpl(
    private val questionDAO: QuestionDAO,
    private val submissionDAO: SubmissionDAO,
): SubmissionRepository {
    override suspend fun submitAnswer(userId: UUID, submitRequest: SubmitRequest): ResponseAlias<Any> {
        var score = 0
        submitRequest.userAnswers.forEach { userAnswer ->
            questionDAO.getQuestionWithAnswersById(userAnswer.questionId)?.let { question ->
                val correctAnswer = question.answers?.find { answer -> answer.isCorrect }
                if (correctAnswer!!.id == userAnswer.answerId) {
                   score += question.score
                }
            }
        }
        submissionDAO.createSubmission(userId, submitRequest.quizId, score).let {
            return Pair(HttpStatusCode.OK, BaseResponse(it))
        }
    }
}