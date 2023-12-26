package dev.timpham.data

import dev.timpham.data.features.answer.repository.AnswerRepository
import dev.timpham.data.features.answer.repository.AnswerRepositoryImpl
import dev.timpham.data.features.category.repository.CategoryRepository
import dev.timpham.data.features.category.repository.CategoryRepositoryImpl
import dev.timpham.data.features.question.repository.QuestionRepository
import dev.timpham.data.features.question.repository.QuestionRepositoryImpl
import dev.timpham.data.features.quiz.repository.QuizRepository
import dev.timpham.data.features.quiz.repository.QuizRepositoryImpl
import dev.timpham.data.features.user.repository.UserRepository
import dev.timpham.data.features.user.repository.UserRepositoryImpl
import dev.timpham.features.authentication.repository.AuthenticationRepository
import dev.timpham.features.authentication.repository.AuthenticationRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get(), get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<QuizRepository> { QuizRepositoryImpl(get(),get(), get(), get()) }
    single<QuestionRepository> { QuestionRepositoryImpl(get()) }
    single<AnswerRepository> { AnswerRepositoryImpl(get()) }
}