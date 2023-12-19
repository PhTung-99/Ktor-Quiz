package dev.timpham.data

import dev.timpham.data.features.answers.dao.AnswerDAO
import dev.timpham.data.features.answers.dao.AnswerDAOImpl
import dev.timpham.data.features.category.dao.CategoryDAO
import dev.timpham.data.features.category.dao.CategoryDAOImpl
import dev.timpham.data.features.question.dao.QuestionDAO
import dev.timpham.data.features.question.dao.QuestionDAOImpl
import dev.timpham.data.features.quiz.dao.QuizDAO
import dev.timpham.data.features.quiz.dao.QuizDAOImpl
import dev.timpham.data.features.userAnswerHistory.dao.UserAnswerHistoryDAO
import dev.timpham.data.features.userAnswerHistory.dao.UserAnswerHistoryDAOImpl
import dev.timpham.data.features.user.dao.UserDAO
import dev.timpham.data.features.user.dao.UserDAOImpl
import dev.timpham.data.features.userTokens.dao.UserTokenDAO
import dev.timpham.data.features.userTokens.dao.UserTokenDAOImpl
import org.koin.dsl.module

val dataModule = module {
    single<UserDAO> { UserDAOImpl() }
    single<UserTokenDAO> { UserTokenDAOImpl() }

    single<CategoryDAO> { CategoryDAOImpl() }
    single<QuizDAO> { QuizDAOImpl() }
    single<QuestionDAO> { QuestionDAOImpl() }
    single<AnswerDAO> { AnswerDAOImpl() }
    single<UserAnswerHistoryDAO> { UserAnswerHistoryDAOImpl() }
}