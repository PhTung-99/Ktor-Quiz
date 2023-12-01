package dev.timpham.data.validation

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.SerializationException

fun Application.validationConfig() {
    install(StatusPages) {
        exception<SerializationException> { call, cause ->
            call.respond(HttpStatusCode.UnprocessableEntity)
        }
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, mapOf("exception" to cause.message))
        }
    }
}
