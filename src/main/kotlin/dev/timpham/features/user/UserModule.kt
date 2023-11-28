package dev.timpham.features.user

import dev.timpham.features.user.repository.UserRepository
import dev.timpham.features.user.repository.UserRepositoryImpl
import org.koin.dsl.module

val userModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}