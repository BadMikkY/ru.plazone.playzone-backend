package ru.plazone.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object UserModel : Table("users") {
    private val login = UserModel.varchar("login", 25)
    private val password = UserModel.varchar("password", 25)
    private val email = UserModel.varchar("email", 30)
    private val username = UserModel.varchar("username", 30)

    fun insert(userDTO: UserDTO) {
        transaction {
            UserModel.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[username] = userDTO.username
                it[email] = userDTO.email ?: ""
            }
        }
    }

    fun fetchUser(login: String): UserDTO {
        val userModel = UserModel.select { UserModel.login.eq(login) }.single()
        return UserDTO(
            login = userModel[UserModel.login],
            password = userModel[password],
            username = userModel[username],
            email = userModel[email]
        )
    }

}