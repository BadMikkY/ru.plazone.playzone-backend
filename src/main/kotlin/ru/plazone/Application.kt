package ru.plazone

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import ru.plazone.features.login.configureLoginRouting
import ru.plazone.features.register.configureRegisterRouting
import ru.plazone.plugins.*

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/playzonetrain",driver = "org.postgresql.Driver","postgres","egor2002")

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureSerialization()
    configureRegisterRouting()
}
