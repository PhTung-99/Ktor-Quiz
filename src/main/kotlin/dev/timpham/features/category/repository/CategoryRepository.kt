package dev.timpham.features.category.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.data.features.category.models.Category
import dev.timpham.data.features.category.models.CategoryRequest
import java.util.UUID

interface CategoryRepository {

    suspend fun getAllCategories(name: String?, isActive: Boolean?): ResponseAlias<List<Category>>

    suspend fun getCategoryById(id: UUID): ResponseAlias<Category?>

    suspend fun createCategory(category: CategoryRequest): ResponseAlias<Category>

    suspend fun updateCategory(id: UUID, category: CategoryRequest): ResponseAlias<Category?>

    suspend fun deleteCategory(id: UUID): ResponseAlias<Boolean>
}