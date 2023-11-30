package dev.timpham.features.quiz

import dev.timpham.authentication.JWTUtils
import dev.timpham.features.quiz.repository.QuizRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*


fun Route.quizRoutes() {
    val quizRepository: QuizRepository by inject()

    route("/quiz") {

        authenticate(JWTUtils.CONFIGURATIONS_KEY) {
            get("") {
                val result = quizRepository.getQuizList()
                call.respond(result.first, result.second)
            }

            get("{id}") {
                val id = call.parameters["id"]
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    val uuidId = UUID.fromString(id)
                    val result = quizRepository.getQuizById(uuidId)
                    call.respond(result.first, result.second)
                }
            }
        }
    }
}