package dev.timpham.plugin.serializable
import dev.timpham.plugin.serializable.serializer.InstantSerializer
import dev.timpham.plugin.serializable.serializer.UUIDSerializer
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.time.Instant
import java.util.UUID

fun Application.configureSerializable() {
    install(ContentNegotiation) {
        json(
            Json {
                encodeDefaults = false
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                serializersModule = SerializersModule  {
                    contextual(UUID::class, UUIDSerializer)
                    contextual(Instant::class, InstantSerializer)
                }
            }
        )
    }
}
