package ru.plazone.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

import ru.plazone.database.tokens.TokenDTO
import ru.plazone.database.tokens.TokenModel
import ru.plazone.database.users.UserModel
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val receive = call.receive<LoginRemoteModule>()


        val userDTO = UserModel.fetchUser(receive.login)


        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            if (userDTO.password == receive.password) {
                val token = UUID.randomUUID().toString()
                TokenModel.insert(
                    TokenDTO(
                        id = UUID.randomUUID().toString(),
                        login = receive.login,
                        token = token
                    )
                )
            } else {
                call.respond(HttpStatusCode.BadRequest, "Incorrect password")
            }
        }
    }

}