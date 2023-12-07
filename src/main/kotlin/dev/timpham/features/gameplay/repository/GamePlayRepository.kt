package dev.timpham.features.gameplay.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.data.features.quiz.models.Quiz
import java.util.*

interface GamePlayRepository {
    suspend fun getGameplay(quizId: UUID): ResponseAlias<Quiz?>

}