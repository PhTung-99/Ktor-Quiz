package dev.timpham.data.features.question.mapper

import dev.timpham.data.features.question.entity.QuestionTable
import dev.timpham.data.features.question.models.Question
import org.jetbrains.exposed.sql.ResultRow

fun resultRowToQuestion(row: ResultRow) = Question(
    id = row[QuestionTable.id].value,
    content = row[QuestionTable.content],
    highlight = row[QuestionTable.highlight],
    score = row[QuestionTable.score],
    quizId = row[QuestionTable.quiz].value,
    answers = listOf(),
    createAtUTC = row[QuestionTable.createdAtUTC],
    isDeleted = row[QuestionTable.isDeleted],
)