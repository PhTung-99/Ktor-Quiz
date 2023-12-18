package dev.timpham.data.features.userRoles.entity

import dev.timpham.data.database.BaseTable
import dev.timpham.data.features.roles.entity.Roles
import dev.timpham.data.features.user.entity.Users

object UserRoles: BaseTable("user_roles") {
    val userId = reference("user_id", Users.id)
    val roleId = reference("role_id", Roles.id)
}