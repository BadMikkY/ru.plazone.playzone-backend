package ru.plazone.features.register

import kotlinx.serialization.Serializable
import java.util.StringTokenizer

@Serializable
data class RegisterRemoteModule(
    val login: String,
    val password: String,
    val email: String
)

data class RegisterResponceRemote(
    val token: String
)
