package dev.timpham.data.features.user.repository

import dev.timpham.common.alias.ResponseAlias
import dev.timpham.common.models.BaseResponse
import dev.timpham.data.features.user.dao.UserDAO
import dev.timpham.data.features.user.models.User
import io.ktor.http.*
import java.util.*

class UserRepositoryImpl(
    private val userDAO: UserDAO
): UserRepository {

    override suspend fun getUserInfo(userId: UUID): ResponseAlias<User?> {
        val user = userDAO.getUserById(userId)
        return user?.let {
            return Pair(
                HttpStatusCode.OK,
                BaseResponse(
                    data = user,
                )
            )
        } ?: kotlin.run {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = "USER_NOT_FOUND"))
        }
    }
}