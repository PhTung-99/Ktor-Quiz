package dev.timpham.data.features.submission.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class SubmitRequest(
    val userSubmits: List<UserSubmit>,
    @Contextual
    val startTime: Instant,
    @Contextual
    val endTime: Instant,
)

@Serializable
data class UserSubmit(
    @Contextual
    val questionId: UUID,
    val answerIds: List<@Contextual UUID>,
)