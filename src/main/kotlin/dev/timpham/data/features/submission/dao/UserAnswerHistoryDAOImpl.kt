package dev.timpham.data.features.submission.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.submission.entity.UserAnswerHistoryEntity
import dev.timpham.data.features.submission.entity.UserAnswerHistory
import dev.timpham.data.features.submission.mapper.entityToSubmission
import dev.timpham.data.features.submission.models.UserAnswer
import dev.timpham.data.features.user.entity.UserEntity
import java.time.Instant
import java.util.UUID

class UserAnswerHistoryDAOImpl: UserAnswerHistoryDAO {
    override suspend fun createSubmission(
        userId: UUID,
        quizId: UUID,
        score: Int,
        startTime: Instant,
        endTime: Instant,
    ): UserAnswer = dbQuery {
        UserAnswerHistoryEntity.new {
            user = UserEntity[userId]
            quiz = QuizEntity[quizId]
            this.score = score
            this.startTime = startTime
            this.endTime = endTime
        }.let(::entityToSubmission)
    }

    override suspend fun getSubmissionByUserId(userId: UUID) {
        UserAnswerHistoryEntity.find {
            UserAnswerHistory.user eq userId
        }
    }

    override suspend fun getSubmission(id: UUID) {
        UserAnswerHistoryEntity.findById(id)
    }
}