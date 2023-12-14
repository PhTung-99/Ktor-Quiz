package dev.timpham.features.quiz

import dev.timpham.authentication.AppJWTPrincipal
import dev.timpham.authentication.JWTUtils
import dev.timpham.data.features.quiz.models.QuizType
import dev.timpham.data.features.quiz.models.request.QuizRequest
import dev.timpham.data.features.userAnswerHistory.models.SubmitRequest
import dev.timpham.features.quiz.repository.QuizRepository
import io.ktor.http.*
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
                val name: String? = call.request.queryParameters["name"]
                val type: QuizType? = call.request.queryParameters["type"]?.let { QuizType.valueOf(it.uppercase()) }
                val result = quizRepository.getQuizList(name = name, type = type, isActive = isActive)
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

            post("submit/{id}") {
                try {
                    val id = UUID.fromString(call.parameters["id"])
                    val request = call.receive<SubmitRequest>()
                    val principal = call.principal<AppJWTPrincipal>()
                    val result = quizRepository.submitAnswer(id, principal!!.user.id ,request)
                    call.respond(result.first, result.second)
                }
                catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message ?: "Bad Request")
                }
            }

            get("leaderboard/{id}") {
                try {
                    val id = UUID.fromString(call.parameters["id"])
                    val result = quizRepository.getLeaderboard(id)
                    call.respond(result.first, result.second)
                }
                catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message ?: "Bad Request")
                }
            }

            get("my-score/{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val principal = call.principal<AppJWTPrincipal>()
                val result = quizRepository.getUserScore(id, principal!!.user.id)
                call.respond(result.first, result.second)
            }
        }
    }
}