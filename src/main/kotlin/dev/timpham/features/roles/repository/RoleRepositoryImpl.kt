package dev.timpham.features.roles.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.common.models.BaseResponse
import dev.timpham.data.features.roles.dao.RoleDAO
import dev.timpham.data.features.roles.models.Role
import dev.timpham.data.features.roles.models.requests.RoleRequest
import io.ktor.http.*
import java.util.UUID

class RoleRepositoryImpl(
    private val roleDAO: RoleDAO
): RoleRepository {
    override suspend fun createRole(roleRequest: RoleRequest): ResponseAlias<Role> {
        roleDAO.createRole(roleRequest).let {
            return Pair(HttpStatusCode.Created, BaseResponse(it))
        }
    }

    override suspend fun getRole(id: UUID): ResponseAlias<Role?> {
        roleDAO.getRole(id)?.let {
            return Pair(HttpStatusCode.OK, BaseResponse(it))
        } ?: run {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_ROLE"))
        }
    }

    override suspend fun getRoles(): ResponseAlias<List<Role>> {
        roleDAO.getRoles().let {
            return Pair(HttpStatusCode.OK, BaseResponse(it))
        }
    }

    override suspend fun updateRole(id: UUID, name: String, description: String): ResponseAlias<Role?> {
        return roleDAO.updateRole(id, name, description)?.let {
            return Pair(HttpStatusCode.OK, BaseResponse(it))
        } ?: run {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "NOT_FOUND_ROLE"))
        }
    }

    override suspend fun deleteRole(id: UUID): Boolean {
        return roleDAO.deleteRole(id)
    }
}