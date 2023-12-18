package dev.timpham.data.features.category.models

import kotlinx.serialization.Serializable

@Serializable
data class CategoryRequest(
    val name: String,
    val description: String,
//    val icon: String,
    val isActive: Boolean,
)