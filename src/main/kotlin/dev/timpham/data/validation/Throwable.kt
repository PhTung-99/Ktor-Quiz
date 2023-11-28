package dev.timpham.data.validation

import dev.timpham.data.models.BaseResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.validationConfig() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, BaseResponse<Nothing>(messageCode = cause.toString()))
        }
    }
}
