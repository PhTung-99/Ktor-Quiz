package dev.timpham.features.question

import dev.timpham.authentication.JWTUtils
import dev.timpham.data.features.question.models.QuestionRequest
import dev.timpham.features.question.repository.QuestionRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.UUID


fun Route.questionRoutes() {

    val questionRepository: QuestionRepository by inject()

    route("/question") {
        authenticate(JWTUtils.CONFIGURATIONS_KEY) {

            get("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val response = questionRepository.getQuestionById(id)
                call.respond(response.first, response.second)
            }

            get("with-answers/{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val response = questionRepository.getQuestionWithAnswer(id)
                call.respond(response.first, response.second)
            }

            get("by-quiz/{quizId}") {
                val quizId = UUID.fromString(call.parameters["quizId"])
                val response = questionRepository.getQuestionsByQuizId(quizId)
                call.respond(response.first, response.second)
            }

            post {
                val request = call.receive<QuestionRequest>()
                val response = questionRepository.createQuestion(request)
                call.respond(response.first, response.second)
            }

            put("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val request = call.receive<QuestionRequest>()
                val response = questionRepository.updateQuestion(id, request)
                call.respond(response.first, response.second)
            }

            delete("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val response = questionRepository.deleteQuestion(id)
                call.respond(response.first, response.second)
            }
        }
    }
}