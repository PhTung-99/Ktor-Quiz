package dev.timpham.data.features.answers.mapper

import dev.timpham.data.features.answers.entity.AnswerEntity
import dev.timpham.data.features.answers.models.Answer

fun entityToAnswer(entity: AnswerEntity): Answer = Answer(
    id = entity.id.value,
    content = entity.content,
    isCorrect = entity.isCorrect,
    questionId = entity.question.id.value,
    createdAtUTC = entity.createdAtUTC,
    isDeleted = entity.isDeleted,
)