package dev.timpham.features.quiz

import dev.timpham.authentication.JWTUtils
import dev.timpham.data.features.quiz.models.request.QuizRequest
import dev.timpham.features.quiz.repository.QuizRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.quizRoutes() {
    val quizRepository: QuizRepository by inject()

    route("/quiz") {

        authenticate(JWTUtils.CONFIGURATIONS_KEY) {
            get("") {
                val isActive: Boolean? = call.request.queryParameters["isActive"]?.toBoolean()
                val result = quizRepository.getQuizList(isActive)
                call.respond(result.first, result.second)
            }

            get("{id}") {
                val uuidId = UUID.fromString(call.parameters["id"])
                val result = quizRepository.getQuizById(uuidId)
                call.respond(result.first, result.second)
            }

            post {
                val request = call.receive<QuizRequest>()
                val result = quizRepository.createQuiz(request)
                call.respond(result.first, result.second)
            }

            put("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val request = call.receive<QuizRequest>()
                val result = quizRepository.updateQuiz(id, request)
                call.respond(result.first, result.second)
            }

            delete("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val result = quizRepository.deleteQuiz(id)
                call.respond(result.first, result.second)
            }

            get("play/{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val result = quizRepository.playQuiz(id)
                call.respond(result.first, result.second)
            }
        }
    }
}