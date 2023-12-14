package dev.timpham.data.features.quiz.entity

import dev.timpham.data.database.BaseEntity
import dev.timpham.data.features.category.entity.CategoryEntity
import dev.timpham.data.features.question.entity.QuestionEntity
import dev.timpham.data.features.question.entity.Questions
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class QuizEntity(id: EntityID<UUID>): BaseEntity(id, Quizzes) {
    companion object : UUIDEntityClass<QuizEntity>(Quizzes)

    var name by Quizzes.name
    var description by Quizzes.description
    var type by Quizzes.type
    var isActive by Quizzes.isActive

    var category by CategoryEntity referencedOn Quizzes.category
    val questions by QuestionEntity referrersOn Questions.quiz
}
