package dev.timpham.features.submission.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.data.features.submission.models.SubmitRequest
import dev.timpham.data.features.submission.models.UserAnswer
import java.util.*

interface SubmissionRepository {
    suspend fun submitAnswer(userId: UUID, submitRequest: SubmitRequest): ResponseAlias<Any>
}