package dev.timpham.features.quiz

import dev.timpham.features.quiz.repository.QuizRepository
import dev.timpham.features.quiz.repository.QuizRepositoryImpl
import org.koin.dsl.module

val quizModule = module {
    single<QuizRepository> { QuizRepositoryImpl(get()) }
}