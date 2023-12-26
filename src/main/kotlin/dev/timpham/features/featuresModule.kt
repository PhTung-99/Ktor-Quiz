package dev.timpham.features

import dev.timpham.features.answer.AnswerService
import dev.timpham.features.category.CategoryService
import dev.timpham.features.question.QuestionService
import dev.timpham.features.quiz.QuizService
import org.koin.dsl.module

val featuresModule = module {
    single<AnswerService> { AnswerService(get()) }
    single<CategoryService> { CategoryService(get()) }
    single<QuestionService> { QuestionService(get()) }
    single<QuizService> { QuizService(get()) }
}
