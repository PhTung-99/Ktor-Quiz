package dev.timpham.logging

import dev.timpham.plugin.ErrorLoggerPlugin
import dev.timpham.plugin.RequestLoggerPlugin
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import org.slf4j.event.Level

fun Application.configureLogging() {
    install(ErrorLoggerPlugin)
    install(RequestLoggerPlugin)
    install(CallLogging) {
        this.level = Level.INFO
    }
}