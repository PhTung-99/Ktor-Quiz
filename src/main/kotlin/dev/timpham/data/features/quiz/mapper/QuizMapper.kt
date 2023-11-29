package dev.timpham.data.features.quiz.mapper

import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.quiz.models.Quiz
import dev.timpham.data.features.user.entity.UserEntity
import org.jetbrains.exposed.sql.ResultRow

fun resultRowToQuiz(row: ResultRow) = Quiz(
    id = row[QuizEntity.id].value,
    name = row[QuizEntity.name],
    description = row[QuizEntity.description],
    isActive = row[QuizEntity.isActive],
    createAtUTC = row[UserEntity.createAtUTC],
    isDeleted = row[UserEntity.isDeleted],
)