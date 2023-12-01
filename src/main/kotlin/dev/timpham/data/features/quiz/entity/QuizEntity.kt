package dev.timpham.data.features.quiz.entity

import dev.timpham.data.database.BaseEntity

object QuizEntity: BaseEntity("quiz") {
    val name = varchar("name", 50)
    val description = varchar("description", 200)
    val isActive = bool("is_active")
}