package dev.timpham.features

import dev.timpham.features.answer.repository.AnswerRepository
import dev.timpham.features.answer.repository.AnswerRepositoryImpl
import dev.timpham.features.authentication.repository.AuthenticationRepository
import dev.timpham.features.authentication.repository.AuthenticationRepositoryImpl
import dev.timpham.features.gameplay.repository.GamePlayRepository
import dev.timpham.features.gameplay.repository.GamePlayRepositoryImpl
import dev.timpham.features.question.repository.QuestionRepository
import dev.timpham.features.question.repository.QuestionRepositoryImpl
import dev.timpham.features.quiz.repository.QuizRepository
import dev.timpham.features.quiz.repository.QuizRepositoryImpl
import dev.timpham.features.user.repository.UserRepository
import dev.timpham.features.user.repository.UserRepositoryImpl
import org.koin.dsl.module

val featuresModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get(), get()) }
    single<QuizRepository> { QuizRepositoryImpl(get()) }
    single<QuestionRepository> { QuestionRepositoryImpl(get(), get()) }
    single<AnswerRepository> { AnswerRepositoryImpl(get()) }
    single<GamePlayRepository> { GamePlayRepositoryImpl(get(), get()) }
}
