package dev.timpham.data.features.roles.models

import dev.timpham.data.features.roles.entity.RoleEntity
import dev.timpham.data.features.roles.mapper.Role

fun entityToRole(entity: RoleEntity): Role = Role(
    id = entity.id.value,
    name = entity.name,
    description = entity.description
)