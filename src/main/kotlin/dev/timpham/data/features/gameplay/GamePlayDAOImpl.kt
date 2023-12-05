package dev.timpham.data.features.gameplay

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.question.entity.QuestionEntity
import dev.timpham.data.features.question.mapper.resultRowToQuestion
import dev.timpham.data.features.question.models.Question
import org.jetbrains.exposed.sql.select
import java.util.UUID

class GamePlayDAOImpl: GamePlayDAO {
    override suspend fun getGameplay(quizId: UUID): List<Question> = dbQuery {
        QuestionEntity.select {
            QuestionEntity.quiz eq quizId
        }
            .mapNotNull(::resultRowToQuestion)
    }

}