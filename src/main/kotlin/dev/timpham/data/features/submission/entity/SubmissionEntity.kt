package dev.timpham.data.features.submission.entity

import dev.timpham.data.database.BaseEntity
import dev.timpham.data.features.question.entity.QuestionEntity
import dev.timpham.data.features.user.entity.UserEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class SubmissionEntity(id: EntityID<UUID>): BaseEntity(id, Submissions) {
    companion object : UUIDEntityClass<SubmissionEntity>(Submissions)

    var user by UserEntity referencedOn Submissions.user
    var question by QuestionEntity referencedOn Submissions.question
    var score by Submissions.score
}