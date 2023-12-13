package dev.timpham.data.features.userAnswerHistory.mapper

import dev.timpham.data.features.user.mapper.entityToUser
import dev.timpham.data.features.userAnswerHistory.entity.UserAnswerHistoryEntity
import dev.timpham.data.features.userAnswerHistory.models.UserAnswerHistory

fun entityToUserAnswer(entity: UserAnswerHistoryEntity) = UserAnswerHistory(
    id = entity.id.value,
    quizId = entity.quiz.id.value,
    score = entity.score,
    startTime = entity.startTime,
    endTime = entity.endTime,
    duration = entity.duration,
    user = entity.user.let(::entityToUser)
)