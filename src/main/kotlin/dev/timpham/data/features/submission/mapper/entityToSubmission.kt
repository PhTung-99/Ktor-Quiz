package dev.timpham.data.features.submission.mapper

import dev.timpham.data.features.submission.entity.UserAnswerHistoryEntity
import dev.timpham.data.features.submission.models.UserAnswer

fun entityToSubmission(entity: UserAnswerHistoryEntity) = UserAnswer(
    id = entity.id.value,
    quizId = entity.quiz.id.value,
    score = entity.score,
    startTime = entity.startTime,
    endTime = entity.endTime,
)