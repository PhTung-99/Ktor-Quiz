package dev.timpham.features.authentication

import dev.timpham.features.authentication.repository.AuthenticationRepository
import dev.timpham.features.authentication.repository.AuthenticationRepositoryImpl
import org.koin.dsl.module

val authenticationModule = module {
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get(), get()) }
}