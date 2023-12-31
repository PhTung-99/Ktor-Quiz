package dev.timpham.features.category

import dev.timpham.core.auth.authentication.JWTUtils
import dev.timpham.core.auth.authorization.Role
import dev.timpham.core.auth.authorization.withRole
import dev.timpham.data.features.category.models.CategoryRequest
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.categoryRoutes() {

    val categoryService: CategoryService by inject()

    route("/category") {
        authenticate(JWTUtils.CONFIGURATIONS_KEY) {
            get {
                val name = call.parameters["name"]
                val isActive = call.parameters["isActive"]?.toBoolean()
                val response = categoryService.getAllCategories(name, isActive)
                call.respond(response.first, response.second)
            }

            get("/{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val response = categoryService.getCategoryById(id)
                call.respond(response.first, response.second)
            }

            withRole(Role.ADMIN) {
                post {
                    val categoryRequest = call.receive<CategoryRequest>()
                    val response = categoryService.createCategory(categoryRequest)
                    call.respond(response.first, response.second)
                }

                put("/{id}") {
                    val id = UUID.fromString(call.parameters["id"])
                    val categoryRequest = call.receive<CategoryRequest>()
                    val response = categoryService.updateCategory(id, categoryRequest)
                    call.respond(response.first, response.second)
                }

                delete("/{id}") {
                    val id = UUID.fromString(call.parameters["id"])
                    val response = categoryService.deleteCategory(id)
                    call.respond(response.first, response.second)
                }
            }
        }
    }
}