package dev.timpham.data.features.category.mapper

import dev.timpham.data.features.category.entity.CategoryEntity
import dev.timpham.data.features.category.models.Category

fun entityToCategory(entity: CategoryEntity) = Category(
    id = entity.id.value,
    name = entity.name,
    description = entity.description,
    icon = entity.icon,
    isActive = entity.isActive,
    createdAtUTC = entity.createdAtUTC,
    isDeleted = entity.isDeleted
)