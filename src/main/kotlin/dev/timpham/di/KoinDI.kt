package dev.timpham.di

import dev.timpham.data.dataModule
import dev.timpham.features.featuresModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(dataModule)

        modules(featuresModule)
    }
}