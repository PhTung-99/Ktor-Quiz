package dev.timpham.data.features.submission.entity

import dev.timpham.data.database.BaseEntity
import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.user.entity.UserEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class UserAnswerHistoryEntity(id: EntityID<UUID>): BaseEntity(id, UserAnswerHistory) {
    companion object : UUIDEntityClass<UserAnswerHistoryEntity>(UserAnswerHistory)

    var user by UserEntity referencedOn UserAnswerHistory.user
    var quiz by QuizEntity referencedOn UserAnswerHistory.quiz
    var score by UserAnswerHistory.score
    var startTime by UserAnswerHistory.startTime
    var endTime by UserAnswerHistory.endTime
}