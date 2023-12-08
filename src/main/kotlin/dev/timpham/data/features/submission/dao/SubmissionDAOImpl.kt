package dev.timpham.data.features.submission.dao

import dev.timpham.data.features.question.entity.QuestionEntity
import dev.timpham.data.features.submission.entity.SubmissionEntity
import dev.timpham.data.features.submission.entity.Submissions
import dev.timpham.data.features.user.entity.UserEntity
import java.util.*

class SubmissionDAOImpl: SubmissionDAO {
    override suspend fun createSubmission(
        userId: UUID,
        questionId: UUID,
        score: Int
    ) {
        SubmissionEntity.new {
            user = UserEntity[userId]
            question = QuestionEntity[questionId]
            this.score = score
        }
    }

    override suspend fun getSubmissionByUserId(userId: UUID) {
        SubmissionEntity.find {
            Submissions.user eq userId
        }
    }

    override suspend fun getSubmission(id: UUID) {
        SubmissionEntity.findById(id)
    }
}