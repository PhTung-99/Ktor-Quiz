package dev.timpham.data.features.submission

import dev.timpham.data.features.user.models.User
import dev.timpham.plugin.serializable.custom.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserSubmission(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    @Serializable(with = UUIDSerializer::class)
    val user: User,
    @Serializable(with = UUIDSerializer::class)
    val questionId: UUID,
    val isCorrect: Boolean,
)
