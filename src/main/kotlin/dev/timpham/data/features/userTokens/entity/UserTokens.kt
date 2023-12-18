package dev.timpham.data.features.userTokens.entity

import dev.timpham.data.database.BaseTable
import dev.timpham.data.database.datetypes.timestampWithTimeZone
import dev.timpham.data.features.user.entity.Users
import java.time.Instant


object UserTokens: BaseTable("user_token") {
    val user = reference("user_id", Users.id)
    val refreshToken = varchar("refresh_token", 500)
    val createAtUTC = timestampWithTimeZone("created_at_UTC").default(Instant.now())
}