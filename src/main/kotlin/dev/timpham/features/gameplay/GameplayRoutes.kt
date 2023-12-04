package dev.timpham.features.gameplay

import dev.timpham.authentication.JWTUtils
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Route.gameplayRoutes() {
    route("/gameplay") {

        authenticate(JWTUtils.CONFIGURATIONS_KEY) {
            get("{quizId}") {

            }
        }
    }
}