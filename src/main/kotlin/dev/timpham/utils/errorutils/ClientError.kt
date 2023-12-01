package dev.timpham.utils.errorutils

import dev.timpham.common.models.BaseResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.clientErrorRoutes() {

    route("success") {
        get("201") {
            call.respond(HttpStatusCode.Created, BaseResponse<Nothing>(messageCode = "201"))
        }

        get("202") {
            call.respond(HttpStatusCode.Accepted, BaseResponse<Nothing>(messageCode = "201"))
        }

        get("203") {
            call.respond(HttpStatusCode.NonAuthoritativeInformation, BaseResponse<Nothing>(messageCode = "203"))
        }
    }

    route("/clientError") {
        get("400") {
            call.respond(HttpStatusCode.BadRequest, BaseResponse<Nothing>(messageCode = "400"))
        }

        get("401") {
            call.respond(HttpStatusCode.Unauthorized, BaseResponse<Nothing>(messageCode = "401"))
        }

        get("403") {
            call.respond(HttpStatusCode.Forbidden, BaseResponse<Nothing>(messageCode = "403"))
        }

        get("404") {
            call.respond(HttpStatusCode.NotFound)
        }

        get ("405") {
            call.respond(HttpStatusCode.MethodNotAllowed)
        }
    }
}