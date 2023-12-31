package ru.plazone.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginRemoteModule(
    val login: String,
    val password: String,
)

@Serializable
data class LoginResponceRemote(
    val token: String
)