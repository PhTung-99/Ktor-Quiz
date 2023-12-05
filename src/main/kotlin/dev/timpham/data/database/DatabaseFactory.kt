package dev.timpham.data.database

import dev.timpham.data.features.answers.entity.AnswerTable
import dev.timpham.data.features.question.entity.QuestionTable
import dev.timpham.data.features.quiz.entity.Quizzes
import dev.timpham.data.features.user.entity.UserTable
import dev.timpham.data.features.user.entity.UserTokenTable
import dev.timpham.property.AppProperties
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    var isConnected = false

    fun init() {
        isConnected = try {
            val driverClassName = AppProperties.postgresProperties.driver
            val jdbcURL = AppProperties.postgresProperties.url
            val user = AppProperties.postgresProperties.user
            val password = AppProperties.postgresProperties.password
            val database = Database.connect(jdbcURL, driverClassName, user, password)
            database.connector() // Attempt to connect
            transaction(database) {
                SchemaUtils.create(UserTable)
                SchemaUtils.create(UserTokenTable)
                SchemaUtils.create(Quizzes)
                SchemaUtils.create(QuestionTable)
                SchemaUtils.create(AnswerTable)
            }
            true
        } catch (e: Exception) {
            println(e.toString())
            false
        }
    }


    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}