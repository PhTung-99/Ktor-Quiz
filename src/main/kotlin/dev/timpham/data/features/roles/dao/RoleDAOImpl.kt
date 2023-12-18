package dev.timpham.data.features.roles.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.roles.entity.RoleEntity
import dev.timpham.data.features.roles.entity.Roles
import dev.timpham.data.features.roles.models.Role
import dev.timpham.data.features.roles.mapper.entityToRole
import dev.timpham.data.features.roles.models.requests.RoleRequest
import java.util.UUID

class RoleDAOImpl: RoleDAO {
    override suspend fun createRole(roleRequest: RoleRequest): Role = dbQuery {
        RoleEntity.new {
            name = roleRequest.name
            description = roleRequest.description
        }.let(::entityToRole)
    }

    override suspend fun getRole(id: UUID): Role? = dbQuery {
        RoleEntity.findById(id)?.let(::entityToRole)
    }

    override suspend fun getRoles(): List<Role> = dbQuery {
        RoleEntity.all().map(::entityToRole)
    }

    override suspend fun updateRole(id: UUID, name: String, description: String): Role? = dbQuery {
        RoleEntity.findById(id)?.apply {
            this.name = name
            this.description = description
        }?.let(::entityToRole)
    }

    override suspend fun deleteRole(id: UUID): Boolean = dbQuery {
        RoleEntity.findById(id)?.apply {
            this.isDeleted = true
        }
        true
    }

    override suspend fun getRolesByUser(): List<Roles> {
        TODO("Not yet implemented")
    }

}