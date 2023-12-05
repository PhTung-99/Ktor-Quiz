package dev.timpham.data.features.quiz.entity

import dev.timpham.data.database.BaseEntity
import dev.timpham.data.features.quiz.models.Quiz
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class QuizEntity(id: EntityID<UUID>): BaseEntity(id, Quizzes) {
    companion object : UUIDEntityClass<QuizEntity>(Quizzes)

    var name by Quizzes.name
    var description by Quizzes.description
    var isActive by Quizzes.isActive

    fun toQuiz(): Quiz {
        return Quiz(
            id = this.id.value,
            name = this.name,
            description = this.description,
            isActive = this.isActive,
            createdAtUTC = this.createdAtUTC,
            isDeleted = this.isDeleted,
        )
    }
}
