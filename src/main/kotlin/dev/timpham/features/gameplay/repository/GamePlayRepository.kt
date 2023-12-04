package dev.timpham.features.gameplay.repository

interface GamePlayRepository {
    suspend fun getGameplay(quizId: Int)

}