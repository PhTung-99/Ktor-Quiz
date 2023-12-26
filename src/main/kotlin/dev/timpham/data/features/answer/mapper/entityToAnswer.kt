package dev.timpham.data.features.answer.mapper

import dev.timpham.data.features.answer.entity.AnswerEntity
import dev.timpham.data.features.answer.models.Answer

fun entityToAnswer(entity: AnswerEntity): Answer = Answer(
    id = entity.id.value,
    content = entity.content,
    isCorrect = entity.isCorrect,
    questionId = entity.question.id.value,
    createdAtUTC = entity.createdAtUTC,
    isDeleted = entity.isDeleted,
)