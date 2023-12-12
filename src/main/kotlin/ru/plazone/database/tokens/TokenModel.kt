package ru.plazone.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object TokenModel : Table() {
    private val id = TokenModel.varchar("id", 25)
    private val login = TokenModel.varchar("login", 25)
    private val token = TokenModel.varchar("token", 50)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            TokenModel.insert {
                it[id] = tokenDTO.id
                it[login] = tokenDTO.login
                it[token] = tokenDTO.token
            }
        }
    }
}