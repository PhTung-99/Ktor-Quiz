package dev.timpham.features.user.repository

import dev.timpham.data.models.BaseResponse
import dev.timpham.data.features.user.dao.UserDAO
import dev.timpham.data.features.user.models.User
import dev.timpham.features.user.constants.UserMessageCode
import io.ktor.http.*
import java.util.*

class UserRepositoryImpl(
    private val userDAO: UserDAO
): UserRepository {

    override suspend fun getUserInfo(userId: UUID): Pair<HttpStatusCode, BaseResponse<User?>> {
        val user = userDAO.getUserById(userId)
        return user?.let {
            return Pair(
                HttpStatusCode.OK,
                BaseResponse(
                    data = user,
                )
            )
        } ?: kotlin.run {
            return Pair(HttpStatusCode.BadRequest, BaseResponse(messageCode = UserMessageCode.USER_NOT_FOUND))
        }
    }
}