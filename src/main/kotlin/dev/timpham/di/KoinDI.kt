package dev.timpham.di

import dev.timpham.data.dataModule
import dev.timpham.features.authentication.authenticationModule
import dev.timpham.features.quiz.quizModule
import dev.timpham.features.user.userModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(dataModule)

        modules(userModule)
        modules(authenticationModule)
        modules(quizModule)
    }
}