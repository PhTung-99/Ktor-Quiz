package dev.timpham.data.features.quiz.mapper

import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.quiz.models.Quiz

fun entityToQuiz(entity: QuizEntity): Quiz = Quiz(
    id = entity.id.value,
    name = entity.name,
    description = entity.description,
    type = entity.type,
    isActive = entity.isActive,
    createdAtUTC = entity.createdAtUTC,
    isDeleted = entity.isDeleted
)