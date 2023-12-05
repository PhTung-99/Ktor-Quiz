package dev.timpham.data.features.quiz.mapper

import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.quiz.models.Quiz

fun resultRowToQuiz(row: QuizEntity) = Quiz(
    id = row.id.value,
    name = row.name,
    description = row.description,
    isActive = row.isActive,
    createAtUTC = row.createAtUTC,
    isDeleted = row.isDeleted,
)