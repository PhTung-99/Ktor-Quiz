package dev.timpham.data.features.category.entity

import dev.timpham.data.database.BaseEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class CategoryEntity(id: EntityID<UUID>): BaseEntity(id, Categories) {
    companion object : UUIDEntityClass<CategoryEntity>(Categories)

    var name by Categories.name
    var description by Categories.description
//    var icon by Categories.icon
    var isActive by Categories.isActive
}