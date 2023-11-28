package dev.timpham.features.authentication.models.requests

data class SignupRequest(val email: String, val password: String, val name: String)
