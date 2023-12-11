package dev.timpham.data.features.submission.dao

import dev.timpham.data.features.quiz.entity.QuizEntity
import dev.timpham.data.features.submission.entity.SubmissionEntity
import dev.timpham.data.features.submission.entity.Submissions
import dev.timpham.data.features.user.entity.UserEntity
import java.time.Instant
import java.util.UUID

class SubmissionDAOImpl: SubmissionDAO {
    override suspend fun createSubmission(
        userId: UUID,
        quizId: UUID,
        score: Int,
        startTime: Instant,
        endTime: Instant,
    ) {
        SubmissionEntity.new {
            user = UserEntity[userId]
            quiz = QuizEntity[quizId]
            this.score = score
            this.startTime = startTime
            this.endTime = endTime
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