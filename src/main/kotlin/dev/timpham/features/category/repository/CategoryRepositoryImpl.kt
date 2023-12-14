package dev.timpham.features.category.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.common.models.BaseResponse
import dev.timpham.data.features.category.dao.CategoryDAO
import dev.timpham.data.features.category.models.Category
import dev.timpham.data.features.category.models.CategoryRequest
import io.ktor.http.*
import java.util.UUID

class CategoryRepositoryImpl(
    private val categoryDAO: CategoryDAO
): CategoryRepository {
    override suspend fun getAllCategories(name: String?, isActive: Boolean?): ResponseAlias<List<Category>> {
        categoryDAO.getAllCategories(name, isActive).let { categories ->
             return Pair(HttpStatusCode.OK, BaseResponse(categories))
        }
    }

    override suspend fun getCategoryById(id: UUID): ResponseAlias<Category?> {
        categoryDAO.getCategoryById(id)?.let {
            return Pair(HttpStatusCode.OK, BaseResponse(it))
        } ?: run {
            return Pair(HttpStatusCode.NotFound, BaseResponse(null))
        }
    }

    override suspend fun createCategory(category: CategoryRequest): ResponseAlias<Category> {
        categoryDAO.createCategory(category).let {
            return Pair(HttpStatusCode.Created, BaseResponse(it))
        }
    }

    override suspend fun updateCategory(id: UUID, category: CategoryRequest): ResponseAlias<Category?> {
        categoryDAO.updateCategory(id, category)?.let {
            return Pair(HttpStatusCode.OK, BaseResponse(it))
        } ?: run {
            return Pair(HttpStatusCode.NotFound, BaseResponse(null))
        }
    }

    override suspend fun deleteCategory(id: UUID): ResponseAlias<Boolean> {
       categoryDAO.deleteCategory(id).let {
           return Pair(HttpStatusCode.OK, BaseResponse(it))
       }
    }
}