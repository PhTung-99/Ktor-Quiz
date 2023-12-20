package dev.timpham.data.validation

import dev.timpham.core.auth.authorization.UnauthorizedAccessException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.SerializationException

fun Application.validationConfig() {
    install(StatusPages) {
        exception<SerializationException> { call, cause ->
            call.respond(HttpStatusCode.UnprocessableEntity, mapOf("exception" to cause.message))
        }
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, mapOf("exception" to cause.message))
        }
        exception<IllegalArgumentException> { call, _ ->
            call.respond(HttpStatusCode.BadRequest, mapOf("messageCode" to "INVALID_REQUEST"))
        }
        exception<BadRequestException> { call, _ ->
            call.respond(HttpStatusCode.BadRequest, mapOf("messageCode" to "INVALID_REQUEST"))
        }
        exception<UnauthorizedAccessException> { call, _ ->
            call.respond(HttpStatusCode.Forbidden, mapOf("messageCode" to "FORBIDDEN"))
        }
    }
}
