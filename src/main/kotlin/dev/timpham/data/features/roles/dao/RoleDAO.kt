package dev.timpham.data.features.roles.dao

import dev.timpham.data.features.roles.entity.Roles
import dev.timpham.data.features.roles.models.Role
import dev.timpham.data.features.roles.models.requests.RoleRequest
import java.util.*

interface RoleDAO {
    suspend fun createRole(roleRequest: RoleRequest): Role

    suspend fun getRole(id: UUID): Role?

    suspend fun getRoles(): List<Role>

    suspend fun updateRole(id: UUID, name: String, description: String): Role?

    suspend fun deleteRole(id: UUID): Boolean

    suspend fun getRolesByUser(): List<Roles>
}