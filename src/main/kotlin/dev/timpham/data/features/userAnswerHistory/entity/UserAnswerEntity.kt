package dev.timpham.data.features.userAnswerHistory.entity

import dev.timpham.data.database.BaseEntity
import dev.timpham.data.features.answers.entity.AnswerEntity
import dev.timpham.data.features.question.entity.QuestionEntity
import dev.timpham.data.features.user.entity.UserEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class UserAnswerEntity(id: EntityID<UUID>): BaseEntity(id, UserAnswerHistory) {
    companion object : UUIDEntityClass<UserAnswerEntity>(UserAnswerHistory)

    var user by UserEntity referencedOn UserAnswerHistory.user
    var question by QuestionEntity referencedOn UserAnswerHistory.question
    var answer by AnswerEntity referencedOn UserAnswerHistory.answer
    var isCorrect by UserAnswerHistory.isCorrect
}