package dev.timpham.data.features.user.entity


import dev.timpham.data.database.BaseTable
import dev.timpham.core.auth.authorization.Role

object Users : BaseTable("user") {
    val name = varchar("name", 50)
    val email = varchar("email", 50).uniqueIndex()
    val password = varchar("password", 100)
    val avatar = varchar("avatar", 100).nullable()
    val role = enumerationByName<Role>("role", 10).default(Role.USER)
}