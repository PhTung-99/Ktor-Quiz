package dev.timpham.data.features.question.mapper

import dev.timpham.data.features.question.entity.QuestionEntity
import dev.timpham.data.features.question.models.Question

fun entityToQuestion(entity: QuestionEntity): Question = Question(
    id = entity.id.value,
    content = entity.content,
    highlight = entity.highlight,
    isMultipleChoice = entity.isMultipleChoice,
    score = entity.score,
    quizId = entity.quiz.id.value,
    createdAtUTC = entity.createdAtUTC,
    isDeleted = entity.isDeleted,
)