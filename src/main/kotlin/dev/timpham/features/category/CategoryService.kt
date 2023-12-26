package dev.timpham.features.category

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.common.models.BaseResponse
import dev.timpham.data.features.category.exception.CategoryNotFoundException
import dev.timpham.data.features.category.models.Category
import dev.timpham.data.features.category.models.CategoryRequest
import dev.timpham.data.features.category.repository.CategoryRepository
import io.ktor.http.*
import java.util.UUID

class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    suspend fun getAllCategories(name: String?, isActive: Boolean?): ResponseAlias<List<Category>> {
        categoryRepository.getAllCategories(name, isActive).let { categories ->
            return ResponseAlias(HttpStatusCode.OK, BaseResponse(categories))
        }
    }

    suspend fun getCategoryById(id: UUID): ResponseAlias<Category> {
        try {
            categoryRepository.getCategoryById(id).let { category ->
                return ResponseAlias(HttpStatusCode.OK, BaseResponse(category))
            }
        }
        catch (e: CategoryNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode = e.message))
        }
    }

    suspend fun createCategory(categoryRequest: CategoryRequest): ResponseAlias<Category> {
        categoryRepository.createCategory(categoryRequest).let { category ->
            return ResponseAlias(HttpStatusCode.Created, BaseResponse(category))
        }
    }

    suspend fun updateCategory(id: UUID, categoryRequest: CategoryRequest): ResponseAlias<Category> {
        try {
            categoryRepository.updateCategory(id, categoryRequest).let { category ->
                return ResponseAlias(HttpStatusCode.OK, BaseResponse(category))
            }
        }
        catch (e: CategoryNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode = e.message))
        }
    }

    suspend fun deleteCategory(id: UUID): ResponseAlias<Boolean> {
        try {
            categoryRepository.deleteCategory(id).let { result ->
                return ResponseAlias(HttpStatusCode.OK, BaseResponse(data = result))
            }
        }
        catch (e: CategoryNotFoundException) {
            return ResponseAlias(HttpStatusCode.BadRequest, BaseResponse(messageCode = e.message))
        }
    }
}