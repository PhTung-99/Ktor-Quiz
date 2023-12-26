package dev.timpham.features.quiz

import dev.timpham.core.auth.authentication.AppJWTPrincipal
import dev.timpham.core.auth.authentication.JWTUtils
import dev.timpham.data.features.quiz.models.QuizType
import dev.timpham.data.features.quiz.models.request.QuizRequest
import dev.timpham.data.features.userAnswerHistory.models.SubmitRequest
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.quizRoutes() {
    val quizService: QuizService by inject()

    route("/quiz") {

        authenticate(JWTUtils.CONFIGURATIONS_KEY) {
            get("") {
                val isActive: Boolean? = call.request.queryParameters["isActive"]?.toBoolean()
                val name: String? = call.request.queryParameters["name"]
                val type: QuizType? = call.request.queryParameters["type"]?.let { QuizType.valueOf(it.uppercase()) }
                val result = quizService.getQuizList(name = name, type = type, isActive = isActive)
                call.respond(result.first, result.second)
            }

            get("{id}") {
                val uuidId = UUID.fromString(call.parameters["id"])
                val result = quizService.getQuizById(uuidId)
                call.respond(result.first, result.second)
            }

            post {
                val request = call.receive<QuizRequest>()
                val result = quizService.createQuiz(request)
                call.respond(result.first, result.second)
            }

            put("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val request = call.receive<QuizRequest>()
                val result = quizService.updateQuiz(id, request)
                call.respond(result.first, result.second)
            }

            delete("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val result = quizService.deleteQuiz(id)
                call.respond(result.first, result.second)
            }

            post("submit/{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val request = call.receive<SubmitRequest>()
                val principal = call.principal<AppJWTPrincipal>()
                val result = quizService.submitAnswer(id, principal!!.userId, request)
                call.respond(result.first, result.second)
            }

            get("leaderboard/{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val result = quizService.getLeaderboard(id)
                call.respond(result.first, result.second)
            }

            get("my-score/{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val principal = call.principal<AppJWTPrincipal>()
                val result = quizService.getUserScore(id, principal!!.userId)
                call.respond(result.first, result.second)
            }
        }
    }
}