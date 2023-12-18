package dev.timpham.features.roles

import dev.timpham.core.authentication.JWTUtils
import dev.timpham.features.roles.repository.RoleRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*


fun Route.rolesRoutes() {

    val roleRoute: RoleRepository by inject()


    route("/roles") {
        authenticate(JWTUtils.CONFIGURATIONS_KEY) {
            get {
                val result = roleRoute.getRoles()
                call.respond(result.first, result.second)
            }

            get("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val result = roleRoute.getRole(id)
                call.respond(result.first, result.second)
            }
        }
    }
}