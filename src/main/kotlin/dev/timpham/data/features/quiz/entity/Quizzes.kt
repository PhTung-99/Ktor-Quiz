package dev.timpham.data.features.quiz.entity

import dev.timpham.data.database.BaseTable
import dev.timpham.data.features.category.entity.Categories
import dev.timpham.data.features.quiz.models.QuizType

object Quizzes: BaseTable("quiz") {
    val name = varchar("name", 50)
    val description = varchar("description", 200)
    val type = enumerationByName<QuizType>("type", 10)
    val isActive = bool("is_active")

    val category = reference("category", Categories)
}
