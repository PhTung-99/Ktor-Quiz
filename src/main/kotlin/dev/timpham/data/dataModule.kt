package dev.timpham.data

import dev.timpham.data.features.user.dao.UserDAO
import dev.timpham.data.features.user.dao.UserDAOImpl
import dev.timpham.data.features.user.dao.UserTokenDAO
import dev.timpham.data.features.user.dao.UserTokenDAOImpl
import org.koin.dsl.module

val dataModule = module {
    single<UserDAO> { UserDAOImpl() }
    single<UserTokenDAO> { UserTokenDAOImpl() }
}