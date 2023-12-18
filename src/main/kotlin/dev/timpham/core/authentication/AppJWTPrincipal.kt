package dev.timpham.core.authentication

import com.auth0.jwt.interfaces.Payload
import dev.timpham.data.features.user.models.User
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

class AppJWTPrincipal(
    payload: Payload,
    val user: User
) : Principal, JWTPayloadHolder(payload)