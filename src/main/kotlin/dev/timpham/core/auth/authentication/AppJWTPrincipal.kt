package dev.timpham.core.auth.authentication

import com.auth0.jwt.interfaces.Payload
import dev.timpham.core.auth.authorization.Role
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import java.util.UUID

class  AppJWTPrincipal(
    payload: Payload,
    val userId: UUID,
    val role: Role,
) : Principal, JWTPayloadHolder(payload)