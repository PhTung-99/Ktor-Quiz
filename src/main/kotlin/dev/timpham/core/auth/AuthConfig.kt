package dev.timpham.core.auth

import dev.timpham.common.constants.Expressions
import dev.timpham.core.auth.authentication.AppJWTPrincipal
import dev.timpham.core.auth.authentication.JWTUtils
import dev.timpham.core.auth.authorization.Role
import dev.timpham.core.auth.authorization.roleBased
import dev.timpham.features.authentication.constants.AuthenticationMessageCode
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import java.util.*

fun Application.configAuthentication() {

    install(Authentication) {
        jwt(JWTUtils.CONFIGURATIONS_KEY) {
            verifier {
                httpAuthHeader ->
                JWTUtils.baseVerifier(httpAuthHeader)
            }
            validate { jwtCredential ->
                val userId = jwtCredential.payload.getClaim("userId").asString()
                val role = jwtCredential.payload.getClaim("role").asString()
                if (userId != "" && role != "") {
                    if (Expressions.UUID_REGEX.matcher(userId).matches()) {
                        val uuid = UUID.fromString(userId)
                        val roleEnum = Role.valueOf(role)
                        AppJWTPrincipal(jwtCredential.payload, uuid, roleEnum)
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

        roleBased {
            extractRoles { principal ->
                //Extract roles from JWT payload
                principal.role
            }
        }
    }

}
