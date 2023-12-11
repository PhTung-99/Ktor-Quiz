package dev.timpham.data.features.submission.entity

import dev.timpham.data.database.BaseEntity
import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.user.entity.UserEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class SubmissionEntity(id: EntityID<UUID>): BaseEntity(id, Submissions) {
    companion object : UUIDEntityClass<SubmissionEntity>(Submissions)

    var user by UserEntity referencedOn Submissions.user
    var quiz by QuizEntity referencedOn Submissions.quiz
    var score by Submissions.score
    var startTime by Submissions.startTime
    var endTime by Submissions.endTime
}