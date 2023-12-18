package dev.timpham.data.database

import dev.timpham.data.features.answers.entity.Answers
import dev.timpham.data.features.category.entity.Categories
import dev.timpham.data.features.question.entity.Questions
import dev.timpham.data.features.quiz.entity.Quizzes
import dev.timpham.data.features.roles.entity.Roles
import dev.timpham.data.features.userRoles.entity.UserRoles
import dev.timpham.data.features.userAnswerHistory.entity.UserAnswerHistories
import dev.timpham.data.features.user.entity.Users
import dev.timpham.data.features.userTokens.entity.UserTokens
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
            database.connector()
            transaction(database) {
                SchemaUtils.create(Roles)
                SchemaUtils.create(Users)
                SchemaUtils.create(UserRoles)
                SchemaUtils.create(UserTokens)
                SchemaUtils.create(Categories)
                SchemaUtils.create(Quizzes)
                SchemaUtils.create(Questions)
                SchemaUtils.create(Answers)
                SchemaUtils.create(UserAnswerHistories)
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