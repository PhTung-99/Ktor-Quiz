package dev.timpham.data.features.category.repository

import dev.timpham.data.features.category.dao.CategoryDAO
import dev.timpham.data.features.category.exception.CategoryNotFoundException
import dev.timpham.data.features.category.models.Category
import dev.timpham.data.features.category.models.CategoryRequest
import java.util.*

class CategoryRepositoryImpl(
    private val categoryDAO: CategoryDAO
): CategoryRepository {
    override suspend fun getAllCategories(name: String?, isActive: Boolean?): List<Category> {
        return categoryDAO.getAllCategories(name, isActive)
    }

    override suspend fun getCategoryById(id: UUID): Category {
        categoryDAO.getCategoryById(id)?.let {
            return it
        } ?: run {
            throw CategoryNotFoundException()
        }
    }

    override suspend fun createCategory(category: CategoryRequest): Category {
        return categoryDAO.createCategory(category)
    }

    override suspend fun updateCategory(id: UUID, category: CategoryRequest): Category {
        categoryDAO.updateCategory(id, category)?.let {
            return it
        } ?: run {
            throw CategoryNotFoundException()
        }
    }

    override suspend fun deleteCategory(id: UUID): Boolean {
       val result = categoryDAO.deleteCategory(id)
        return if (result) {
            true
        } else {
            throw CategoryNotFoundException()
        }
    }
}