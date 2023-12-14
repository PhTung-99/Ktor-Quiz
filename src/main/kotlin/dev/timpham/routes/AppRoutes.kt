package dev.timpham.routes

import dev.timpham.features.answer.answerRoutes
import dev.timpham.features.authentication.authenticationRoute
import dev.timpham.features.category.categoryRoutes
import dev.timpham.features.question.questionRoutes
import dev.timpham.features.quiz.quizRoutes
import dev.timpham.utils.errorutils.clientErrorRoutes
import dev.timpham.utils.errorutils.serverErrorRoutes
import dev.timpham.features.user.userRoutes
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File
import java.nio.file.Paths

fun Application.configureRouting() {
    val pathImage = "${Paths.get("").toAbsolutePath()}/static-content/images/"
    routing {
        staticFiles("/static-content/images", File(pathImage))
        clientErrorRoutes()
        serverErrorRoutes()
        userRoutes()
        quizRoutes()
        questionRoutes()
        answerRoutes()
        healthRoute()
        authenticationRoute()
        categoryRoutes()
    }
}
