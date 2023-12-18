package dev.timpham.data.database

import dev.timpham.property.AppProperties
import kotlinx.coroutines.Dispatchers
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object DatabaseFactory {

    var isConnected = false

    fun init() {
        isConnected = try {
            val driverClassName = AppProperties.postgresProperties.driver
            val jdbcURL = AppProperties.postgresProperties.url
            val user = AppProperties.postgresProperties.user
            val password = AppProperties.postgresProperties.password
            val flyway = Flyway.configure().dataSource(jdbcURL, user, password).load()
            flyway.migrate()
            Database.connect(jdbcURL, driverClassName, user, password)
            true
        } catch (e: Exception) {
            println(e.toString())
            false
        }
    }


    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}