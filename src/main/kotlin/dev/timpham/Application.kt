package dev.timpham

import dev.timpham.core.auth.configAuthentication
import dev.timpham.data.database.DatabaseFactory
import dev.timpham.data.redis.RedisClient
import dev.timpham.data.validation.validationConfig
import dev.timpham.di.configureKoin
import dev.timpham.logging.configureLogging
import dev.timpham.plugin.configCORS
import dev.timpham.plugin.serializable.configureSerializable
import dev.timpham.property.AppProperties
import dev.timpham.routes.configureRouting
import dev.timpham.utils.fileInit
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    AppProperties.init(environment.config)
    configureKoin()
    fileInit()
    DatabaseFactory.init()
    RedisClient.init()
    configCORS()
    validationConfig()
    configureSerializable()
    configureLogging()
    configAuthentication()
    configureRouting()
}
