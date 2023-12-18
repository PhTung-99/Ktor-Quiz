package dev.timpham.data.features.roles.entity

import dev.timpham.data.database.BaseTable

object Roles: BaseTable("roles") {
    val name = varchar("name", 255)
    val description = varchar("description", 255)
    val isActive = bool("is_active")
}