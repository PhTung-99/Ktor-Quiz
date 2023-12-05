package dev.timpham.data.features.gameplay

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.question.models.Question
import java.util.UUID

class GamePlayDAOImpl: GamePlayDAO {
    override suspend fun getGameplay(quizId: UUID): List<Question> = dbQuery {
        TODO()
    }

}