package ru.plazone.features.register

import ch.qos.logback.core.subst.Token
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.plazone.database.tokens.TokenDTO
import ru.plazone.database.tokens.TokenModel
import ru.plazone.database.users.UserDTO
import ru.plazone.database.users.UserModel
import ru.plazone.utils.isValidEmail
import java.util.*

class RegisterController(var call: ApplicationCall) {
    suspend fun registerNewUser() {
        val registerRemoteModule = call.receive<RegisterRemoteModule>()
        if(!registerRemoteModule.email.isValidEmail()){
            call.respond(HttpStatusCode.BadRequest,"Email is not valid")
        }

        val userDTO = UserModel.fetchUser(registerRemoteModule.login)

        if (userDTO !== null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()
            UserModel.insert(
                UserDTO(
                    login = registerRemoteModule.login,
                    password = registerRemoteModule.password,
                    email = registerRemoteModule.email,
                    username = ""
                )
            )
            TokenModel.insert(
                TokenDTO(
                    id = UUID.randomUUID().toString(),
                    login = registerRemoteModule.login,
                    token = token
                )
            )
        }
    }
}