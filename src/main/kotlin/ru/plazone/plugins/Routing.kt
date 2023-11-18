package ru.plazone.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class Test(
    val text: String
)

fun Application.configureRouting() {
    routing {
        get("/login") {
            call.respond(listOf(Test(text = "Kotlin Rulez"),Test(text = "Egor Lapkovski")))
        }
    }
}
