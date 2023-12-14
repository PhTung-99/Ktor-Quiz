package dev.timpham.data.features.category.entity

import dev.timpham.data.database.BaseTable

object Categories: BaseTable("categories") {
    val name = varchar("content", 500)
    val description = varchar("description", 500)
    val icon = varchar("icon", 500)
    val isActive = bool("is_active")
}