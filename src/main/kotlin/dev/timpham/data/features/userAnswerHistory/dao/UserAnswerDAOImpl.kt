package dev.timpham.data.features.userAnswerHistory.dao

import dev.timpham.data.features.answers.entity.AnswerEntity
import dev.timpham.data.features.answers.entity.Answers
import dev.timpham.data.features.answers.mapper.entityToAnswer
import dev.timpham.data.features.question.entity.QuestionEntity
import dev.timpham.data.features.user.entity.UserEntity
import dev.timpham.data.features.userAnswerHistory.entity.UserAnswerEntity
import java.util.UUID

class UserAnswerDAOImpl: UserAnswerDAO {
    override suspend fun submitAnswer(userId: UUID, questionId: UUID, answerId: UUID) {
        val answers = AnswerEntity.find {
            Answers.question eq questionId
        }.map(::entityToAnswer)
        val isCorrect = answers.find { it.id == answerId }?.isCorrect ?: false
        UserAnswerEntity.new {
            this.user = UserEntity[userId]
            this.question = QuestionEntity[questionId]
            this.answer = AnswerEntity[answerId]
            this.isCorrect = isCorrect
        }
    }
}