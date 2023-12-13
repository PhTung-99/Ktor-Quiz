package dev.timpham.data.database.customexpression

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.QueryBuilder
import java.time.Instant

class TimeDifferenceExpression(private val endTime: Column<Instant>, private val startTime: Column<Instant>) : Expression<Long>() {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder {
        append("EXTRACT(EPOCH FROM (")
        endTime.toQueryBuilder(queryBuilder)
        append(" - ")
        startTime.toQueryBuilder(queryBuilder)
        append("))")
    }

}