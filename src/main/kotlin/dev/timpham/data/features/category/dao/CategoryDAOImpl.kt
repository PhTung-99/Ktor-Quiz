package dev.timpham.data.features.category.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.category.entity.Categories
import dev.timpham.data.features.category.entity.CategoryEntity
import dev.timpham.data.features.category.mapper.entityToCategory
import dev.timpham.data.features.category.models.Category
import dev.timpham.data.features.category.models.CategoryRequest
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.lowerCase
import org.jetbrains.exposed.sql.selectAll
import java.util.UUID

class CategoryDAOImpl: CategoryDAO {
    override suspend fun getAllCategories(name: String?, isActive: Boolean?): List<Category> = dbQuery {
        val query = Categories.selectAll()
        name?.let {
            query.andWhere { Categories.name.lowerCase() like  "%${name.trim().lowercase()}%"  }
        }
        isActive?.let {
            query.andWhere { Categories.isActive eq isActive  }
        }
        CategoryEntity.wrapRows(query).map(::entityToCategory)
    }

    override suspend fun getCategoryById(id: UUID): Category? = dbQuery {
        CategoryEntity.findById(id)?.let(::entityToCategory)
    }

    override suspend fun createCategory(categoryRequest: CategoryRequest): Category = dbQuery {
        CategoryEntity.new {
            name = categoryRequest.name
            description = categoryRequest.description
//            icon = categoryRequest.icon
            isActive = categoryRequest.isActive
        }.let(::entityToCategory)
    }

    override suspend fun updateCategory(id: UUID, categoryRequest: CategoryRequest): Category? = dbQuery {
        CategoryEntity.findById(id)?.apply {
            name = categoryRequest.name
            description = categoryRequest.description
//            icon = categoryRequest.icon
            isActive = categoryRequest.isActive
        }?.let(::entityToCategory)
    }

    override suspend fun deleteCategory(id: UUID): Boolean = dbQuery {
        CategoryEntity.findById(id)?.let {
            it.delete()
            return@dbQuery it.isDeleted
        } ?: false
    }
}