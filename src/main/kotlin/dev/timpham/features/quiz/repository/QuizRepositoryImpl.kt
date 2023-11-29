package dev.timpham.features.quiz.repository

import dev.timpham.data.features.quiz.dao.QuizDAO
import dev.timpham.data.features.quiz.models.Quiz
import java.util.UUID

class QuizRepositoryImpl(
    private val quizDao: QuizDAO
): QuizRepository {
    override suspend fun createQuiz(name: String, description: String, isActive: Boolean): Quiz? {
        return quizDao.createQuiz(name, description, isActive)
    }

    override suspend fun getQuizById(id: UUID): Quiz? {
       return quizDao.getQuizById(id)
    }

    override suspend fun getQuizList(): List<Quiz> {
        return quizDao.getQuizList()
    }

    override suspend fun updateQuiz(id: UUID, name: String, description: String, isActive: Boolean): Quiz? {
        return quizDao.updateQuiz(id, name, description, isActive)
    }

    override suspend fun deleteQuiz(id: UUID): Boolean {
        return quizDao.deleteQuiz(id)
    }

}