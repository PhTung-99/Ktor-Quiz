package dev.timpham.data.features.answers.mapper

import dev.timpham.data.features.answers.entity.AnswerTable
import dev.timpham.data.features.answers.models.Answer
import org.jetbrains.exposed.sql.ResultRow

fun resultRowToAnswer(row: ResultRow) = Answer(
    id = row[AnswerTable.id].value,
    content = row[AnswerTable.content],
    isCorrect = row[AnswerTable.isCorrect],
    questionId = row[AnswerTable.question].value,
    createAtUTC = row[AnswerTable.createdAtUTC],
    isDeleted = row[AnswerTable.isDeleted],
)