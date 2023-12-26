package dev.timpham.data.features.category.repository

import dev.timpham.data.features.category.models.Category
import dev.timpham.data.features.category.models.CategoryRequest
import java.util.UUID

interface CategoryRepository {

    suspend fun getAllCategories(name: String?, isActive: Boolean?): List<Category>

    suspend fun getCategoryById(id: UUID): Category

    suspend fun createCategory(category: CategoryRequest): Category

    suspend fun updateCategory(id: UUID, category: CategoryRequest): Category

    suspend fun deleteCategory(id: UUID): Boolean
}