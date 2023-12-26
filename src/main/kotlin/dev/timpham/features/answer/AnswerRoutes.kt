package dev.timpham.features.answer

import dev.timpham.core.auth.authentication.JWTUtils
import dev.timpham.data.features.answer.models.AnswerRequest
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.answerRoutes() {
    val answerService: AnswerService by inject()

    route("/answer") {
        authenticate(JWTUtils.CONFIGURATIONS_KEY) {
            get("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val result = answerService.getAnswerById(id)
                call.respond(result.first, result.second)
            }

            get("by-question/{questionId}") {
                val questionId = UUID.fromString(call.parameters["questionId"])
                val result = answerService.getAnswersByQuestionId(questionId)
                call.respond(result.first, result.second)
            }

            post {
                val request = call.receive<AnswerRequest>()
                val result = answerService.createAnswer(request)
                call.respond(result.first, result.second)
            }

            put("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val request = call.receive<AnswerRequest>()
                val result = answerService.updateAnswer(id, request)
                call.respond(result.first, result.second)
            }

            delete("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val result = answerService.deleteAnswer(id)
                call.respond(result.first, result.second)
            }
        }
    }
}