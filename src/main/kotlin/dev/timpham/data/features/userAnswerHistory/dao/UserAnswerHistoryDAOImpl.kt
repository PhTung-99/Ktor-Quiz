package dev.timpham.data.features.userAnswerHistory.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.user.entity.UserEntity
import dev.timpham.data.features.user.entity.Users
import dev.timpham.data.features.userAnswerHistory.entity.UserAnswerHistories
import dev.timpham.data.features.userAnswerHistory.entity.UserAnswerHistoryEntity
import dev.timpham.data.features.userAnswerHistory.mapper.entityToUserAnswer
import dev.timpham.data.features.userAnswerHistory.models.UserAnswerHistory
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.select
import java.time.Instant
import java.util.*

class UserAnswerHistoryDAOImpl : UserAnswerHistoryDAO {
    override suspend fun createSubmission(
        userId: UUID,
        quizId: UUID,
        score: Int,
        startTime: Instant,
        endTime: Instant,
    ): UserAnswerHistory = dbQuery {
        UserAnswerHistoryEntity.new {
            user = UserEntity[userId]
            quiz = QuizEntity[quizId]
            this.score = score
            this.startTime = startTime
            this.endTime = endTime
        }.let(::entityToUserAnswer)
    }

    override suspend fun getSortedUserHistory(quizId: UUID): List<UserAnswerHistory> = dbQuery {
        UserAnswerHistories
            .join(Users, JoinType.RIGHT, UserAnswerHistories.user, Users.id)
            .slice(UserAnswerHistories.columns + UserAnswerHistories.duration)
            .select {
                UserAnswerHistories.quiz eq quizId
            }
            .orderBy(UserAnswerHistories.score, SortOrder.DESC)
            .orderBy(UserAnswerHistories.duration, SortOrder.ASC)
            .distinctBy { it[UserAnswerHistories.user] }
            .map {
                UserAnswerHistoryEntity.wrapRow(it).let(::entityToUserAnswer)
            }
    }

    override suspend fun getUserHistory(userId: UUID, quizId: UUID): UserAnswerHistory? {
        return this.getSortedUserHistory(quizId)
            .firstOrNull { it.user?.id == userId }
    }
}