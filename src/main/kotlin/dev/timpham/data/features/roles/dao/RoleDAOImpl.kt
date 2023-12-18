package dev.timpham.data.features.roles.dao

import dev.timpham.data.database.DatabaseFactory.dbQuery
import dev.timpham.data.features.roles.entity.RoleEntity
import dev.timpham.data.features.roles.entity.Roles
import dev.timpham.data.features.roles.mapper.Role
import dev.timpham.data.features.roles.models.entityToRole
import java.util.UUID

class RoleDAOImpl: RoleDAO {
    override suspend fun createRole(name: String, description: String): Role = dbQuery {
        RoleEntity.new {
            this.name = name
            this.description = description
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