package dev.timpham.common.alias

import dev.timpham.common.models.BaseResponse
import io.ktor.http.*

// create alias in kotlin
typealias ResponseAlias<T> = Pair<HttpStatusCode, BaseResponse<T>>