package dev.timpham.data.features.quiz.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class QuizType {
    @SerialName("normal")
    NORMAL,
    @SerialName("race")
    RACE,
    @SerialName("buzz")
    BUZZ,
}