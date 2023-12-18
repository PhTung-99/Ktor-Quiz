package dev.timpham.features.roles

import dev.timpham.core.authentication.JWTUtils
import dev.timpham.data.features.roles.models.requests.RoleRequest
import dev.timpham.features.roles.repository.RoleRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*


fun Route.rolesRoutes() {

    val roleRepository: RoleRepository by inject()


    route("/roles") {
        authenticate(JWTUtils.CONFIGURATIONS_KEY) {
            get {
                val result = roleRepository.getRoles()
                call.respond(result.first, result.second)
            }

            get("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val result = roleRepository.getRole(id)
                call.respond(result.first, result.second)
            }

            post {
                val request = call.receive<RoleRequest>()
                val result = roleRepository.createRole(request)
                call.respond(result.first, result.second)
            }

            put("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val request = call.receive<RoleRequest>()
                val result = roleRepository.updateRole(id, request.name, request.description)
                call.respond(result.first, result.second)
            }

            delete("{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val result = roleRepository.deleteRole(id)
                call.respond(result)
            }
        }
    }
}