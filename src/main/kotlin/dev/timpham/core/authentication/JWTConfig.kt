package dev.timpham.core.authentication

import dev.timpham.common.constants.Expressions
import dev.timpham.features.authentication.constants.AuthenticationMessageCode
import dev.timpham.features.user.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject
import java.util.*

fun Application.configAuthentication() {

    val userRepository: UserRepository by inject()

    install(Authentication) {
        jwt(JWTUtils.CONFIGURATIONS_KEY) {
            verifier {
                httpAuthHeader ->
                JWTUtils.baseVerifier(httpAuthHeader)
            }
            validate { jwtCredential ->
                val userId = jwtCredential.payload.getClaim("userId").asString()
                if (userId != "") {
                    if (Expressions.UUID_REGEX.matcher(userId).matches()) {
                        val uuid = UUID.fromString(userId)
                        userRepository.getUserInfo(uuid).second.data?.let { user ->
                            AppJWTPrincipal(jwtCredential.payload, user)
                        }
                    }
                    else {
                        null
                    }
                } else {
                    null
                }
            }

            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, mapOf(
                    "messageCode" to AuthenticationMessageCode.INVALID_TOKEN)
                )
            }
        }
    }
}
