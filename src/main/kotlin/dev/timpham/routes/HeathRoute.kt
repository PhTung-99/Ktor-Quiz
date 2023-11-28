package dev.timpham.routes

import dev.timpham.data.database.DatabaseFactory
import dev.timpham.data.models.HealthCheck
import dev.timpham.data.redis.RedisClient
import dev.timpham.property.AppProperties
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.healthRoute() {
    route("/health") {
        get {
            call.respond(HttpStatusCode.OK, HealthCheck(
                env = AppProperties.env,
                database = if(DatabaseFactory.isConnected) "connected" else "unconnected",
                redis = if(RedisClient.isConnected) "connected" else "unconnected",
            ))
        }
    }
}