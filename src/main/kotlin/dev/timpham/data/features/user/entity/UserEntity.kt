package dev.timpham.data.features.user.entity

import dev.timpham.data.database.BaseEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class UserEntity(id: EntityID<UUID>): BaseEntity(id, Users) {
    companion object : UUIDEntityClass<UserEntity>(Users)

    var name by Users.name
    var password by Users.password
    var email by Users.email
    var avatar by Users.avatar
    var role by Users.role
}