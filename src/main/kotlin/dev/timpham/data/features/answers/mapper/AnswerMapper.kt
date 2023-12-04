package dev.timpham.data.features.answers.mapper

import dev.timpham.data.features.answers.entity.AnswerEntity
import dev.timpham.data.features.answers.models.Answer
import org.jetbrains.exposed.sql.ResultRow

fun resultRowToAnswer(row: ResultRow) = Answer(
    id = row[AnswerEntity.id].value,
    content = row[AnswerEntity.content],
    isCorrect = row[AnswerEntity.isCorrect],
    questionId = row[AnswerEntity.questionId],
    createAtUTC = row[AnswerEntity.createAtUTC],
    isDeleted = row[AnswerEntity.isDeleted],
)