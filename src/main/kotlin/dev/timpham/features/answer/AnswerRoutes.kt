package dev.timpham.features.answer

import dev.timpham.authentication.JWTUtils
import dev.timpham.features.answer.models.AnswerRequest
import dev.timpham.features.answer.repository.AnswerRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.answerRoutes() {
    val answerRepository: AnswerRepository by inject()

    route("/answer") {
        authenticate(JWTUtils.CONFIGURATIONS_KEY) {
            get("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val result = answerRepository.getAnswerById(id)
                call.respond(result.first, result.second)
            }

            get("by-question/{questionId}") {
                val questionId = UUID.fromString(call.parameters["questionId"])
                val result = answerRepository.getAnswersByQuestionId(questionId)
                call.respond(result.first, result.second)
            }

            post {
                val request = call.receive<AnswerRequest>()
                val result = answerRepository.createAnswer(request)
                call.respond(result.first, result.second)
            }

            put("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val request = call.receive<AnswerRequest>()
                val result = answerRepository.updateAnswer(id, request)
                call.respond(result.first, result.second)
            }

            delete("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val result = answerRepository.deleteAnswer(id)
                call.respond(result.first, result.second)
            }
        }
    }
}