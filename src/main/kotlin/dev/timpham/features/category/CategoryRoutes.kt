package dev.timpham.features.category

import dev.timpham.authentication.JWTUtils
import dev.timpham.data.features.category.models.CategoryRequest
import dev.timpham.features.category.repository.CategoryRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.categoryRoutes() {

    val categoryRepository: CategoryRepository by inject()

    route("/category") {
        authenticate(JWTUtils.CONFIGURATIONS_KEY) {
            get {
                val name = call.parameters["name"]
                val isActive = call.parameters["isActive"]?.toBoolean()
                val response = categoryRepository.getAllCategories(name, isActive)
                call.respond(response.first, response.second)
            }

            get("/{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val response = categoryRepository.getCategoryById(id)
                call.respond(response.first, response.second)
            }

            post {
                val categoryRequest = call.receive<CategoryRequest>()
                val response = categoryRepository.createCategory(categoryRequest)
                call.respond(response.first, response.second)
            }

            put("/{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val categoryRequest = call.receive<CategoryRequest>()
                val response = categoryRepository.updateCategory(id, categoryRequest)
                call.respond(response.first, response.second)
            }

            delete("/{id}") {
                val id = UUID.fromString(call.parameters["id"])
                val response = categoryRepository.deleteCategory(id)
                call.respond(response.first, response.second)
            }
        }
    }
}