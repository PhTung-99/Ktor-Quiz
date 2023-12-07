package dev.timpham.features.gameplay

import dev.timpham.authentication.JWTUtils
import dev.timpham.features.gameplay.repository.GamePlayRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Route.gameplayRoutes() {

    val gamePlayRepository: GamePlayRepository by inject()

    route("/gameplay") {

        authenticate(JWTUtils.CONFIGURATIONS_KEY) {
            get("{quizId}") {
                val id = UUID.fromString(call.parameters["quizId"])
                val result = gamePlayRepository.getGameplay(id)
                call.respond(result.first, result.second)
            }
        }
    }
}