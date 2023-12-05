package dev.timpham.data.features.user.entity

import dev.timpham.data.database.datetypes.timestampWithTimeZone
import org.jetbrains.exposed.dao.id.UUIDTable
import java.time.Instant


object UserTokenTable: UUIDTable("user_token") {
    val userId = reference("user_id", UserTable.id)
    val refreshToken = varchar("refresh_token", 500)
    val createAtUTC = timestampWithTimeZone("created_at_UTC").default(Instant.now())
}