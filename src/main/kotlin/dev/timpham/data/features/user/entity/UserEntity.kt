package dev.timpham.data.features.user.entity


import dev.timpham.data.database.BaseEntity

object UserEntity : BaseEntity("user") {
    val name = varchar("name", 50)
    val email = varchar("email", 50).uniqueIndex()
    val password = varchar("password", 100)
    val avatar = varchar("avatar", 100).nullable()
}