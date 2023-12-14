package dev.timpham.data.features.category.dao

import dev.timpham.data.features.category.models.Category
import dev.timpham.data.features.category.models.CategoryRequest
import java.util.*

interface CategoryDAO {
    suspend fun getAllCategories(name: String?, isActive: Boolean?): List<Category>
    suspend fun getCategoryById(id: UUID): Category?
    suspend fun createCategory(categoryRequest: CategoryRequest): Category
    suspend fun updateCategory(id: UUID, categoryRequest: CategoryRequest): Category?
    suspend fun deleteCategory(id: UUID): Boolean
}