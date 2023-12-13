package dev.timpham.data.features.userAnswerHistory.entity

import dev.timpham.data.database.BaseEntity
import dev.timpham.data.database.customexpression.DurationExpression
import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.user.entity.UserEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.Query
import java.util.*

class UserAnswerHistoryEntity(id: EntityID<UUID>): BaseEntity(id, UserAnswerHistories) {
    companion object : UUIDEntityClass<UserAnswerHistoryEntity>(UserAnswerHistories) {
        override fun searchQuery(op: Op<Boolean>): Query {
            return super.searchQuery(op).adjustSlice {
                slice(columns + DurationExpression(UserAnswerHistories.endTime, UserAnswerHistories.startTime))
            }
        }
    }

    var user by UserEntity referencedOn UserAnswerHistories.user
    var quiz by QuizEntity referencedOn UserAnswerHistories.quiz
    var score by UserAnswerHistories.score
    var startTime by UserAnswerHistories.startTime
    var endTime by UserAnswerHistories.endTime
    val duration: Long
        get() = readValues[UserAnswerHistories.duration]
}