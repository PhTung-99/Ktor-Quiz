package dev.timpham.features.quiz.models

import kotlinx.serialization.Serializable

@Serializable
data class QuizRequest(
    val name: String,
    val description: String,
    val isActive: Boolean,
)
