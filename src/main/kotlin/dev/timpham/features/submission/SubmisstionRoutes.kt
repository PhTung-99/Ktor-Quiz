package dev.timpham.features.submission

import dev.timpham.authentication.JWTUtils
import dev.timpham.data.features.submission.models.SubmitRequest
import dev.timpham.features.submission.repository.SubmissionRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.submissionRoute() {
    val submissionRepository: SubmissionRepository by inject()

    route("/submission") {

        authenticate(JWTUtils.CONFIGURATIONS_KEY) {
            post {
                val request = call.receive<SubmitRequest>()
                val result = submissionRepository.submitAnswer(request)
                call.respond(result.first, result.second)
            }
        }
    }
}
