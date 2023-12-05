package dev.timpham.data.features.question.mapper

import dev.timpham.data.features.question.entity.QuestionEntity
import dev.timpham.data.features.question.models.Question
import org.jetbrains.exposed.sql.ResultRow

fun resultRowToQuestion(row: ResultRow) = Question(
    id = row[QuestionEntity.id].value,
    content = row[QuestionEntity.content],
    highlight = row[QuestionEntity.highlight],
    score = row[QuestionEntity.score],
    quizId = row[QuestionEntity.quiz].value,
    answers = listOf(),
    createAtUTC = row[QuestionEntity.createAtUTC],
    isDeleted = row[QuestionEntity.isDeleted],
)