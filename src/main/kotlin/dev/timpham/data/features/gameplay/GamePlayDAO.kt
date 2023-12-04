package dev.timpham.data.features.gameplay

import dev.timpham.data.features.question.models.Question
import java.util.UUID

interface GamePlayDAO {
    suspend fun getGameplay(quizId: UUID): List<Question>
}