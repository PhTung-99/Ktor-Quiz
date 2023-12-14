package dev.timpham.data.features.quiz.mapper

import dev.timpham.data.features.question.mapper.entityToQuestion
import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.quiz.models.Quiz

fun entityToQuiz(entity: QuizEntity): Quiz = Quiz(
    id = entity.id.value,
    name = entity.name,
    description = entity.description,
    type = entity.type,
    questions = entity.questions.map(::entityToQuestion),
    isActive = entity.isActive,
    createdAtUTC = entity.createdAtUTC,
    isDeleted = entity.isDeleted
)