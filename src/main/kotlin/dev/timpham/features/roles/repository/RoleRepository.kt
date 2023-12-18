package dev.timpham.features.roles.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.data.features.roles.mapper.Role
import java.util.UUID

interface RoleRepository {
    suspend fun createRole(name: String, description: String): Role

    suspend fun getRole(id: UUID): ResponseAlias<Role?>

    suspend fun getRoles(): ResponseAlias<List<Role>>

    suspend fun updateRole(id: UUID, name: String, description: String): Role?

    suspend fun deleteRole(id: UUID): Boolean
}