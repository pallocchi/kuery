package kuery.clauses

import kuery.types.Predicate
import kuery.types.Query
import kuery.types.queryOf

/**
 * Any kind of clause in a query.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/SQL_syntax)
 */
internal interface Clause {
    val statement: String
}

/**
 * The SELECT clause retrieves data from one or more tables, or expressions.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/Select_(SQL))
 */
internal class Select(columns: Array<out Any>) : Clause {
    override val statement = "SELECT ${columns.joinToString(", ")}"
    override fun toString() = statement
}

/**
 * The FROM clause, which indicates the table(s) to retrieve data from.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/From_(SQL))
 */
internal class From(table: String) : Clause {
    override val statement = "FROM $table"
    override fun toString() = statement
}

/**
 * The WHERE clause includes a comparison predicate, which restricts the rows returned by the query.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/Where_(SQL))
 */
internal class Where(predicate: Predicate) : Clause {
    override val statement = "WHERE $predicate"
    override fun toString() = statement
}

/**
 * The GROUP BY clause projects rows having common values into a smaller set of rows.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/Group_by_(SQL))
 */
internal class GroupBy(columns: Array<out Any>) : Clause {
    override val statement = "GROUP BY ${columns.joinToString(", ")}"
    override fun toString() = statement
}

/**
 * The ORDER BY clause identifies which columns to use to sort the resulting data, and in which direction to sort them.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/Order_by_(SQL))
 */
internal class OrderBy(columns: Array<out Any>) : Clause {
    override val statement = "ORDER BY ${columns.joinToString(", ")}"
    override fun toString() = statement
}

/**
 * The OFFSET clause specifies the number of rows to skip before starting to return data.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/SQL_syntax)
 */
internal class Offset(rows: Long) : Clause {
    override val statement = "OFFSET $rows"
    override fun toString() = statement
}

/**
 * The LIMIT clause specifies the number of rows to return.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/SQL_syntax)
 */
internal class Limit(rows: Int) : Clause {
    override val statement = "LIMIT $rows"
    override fun toString() = statement
}

/**
 * The SELECT clause retrieves columns from one or more tables, or expressions.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/Select_(SQL))
 *
 * @sample kuery.clauses.ClausesTest.selectAll
 */
fun select() = select("*")

/**
 * The SELECT clause retrieves [columns] from one or more tables, or expressions.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/Select_(SQL))
 *
 * @sample kuery.clauses.ClausesTest.selectCol
 */
fun select(vararg columns: Any) = queryOf(Select(columns).statement)

/**
 * The FROM clause, which indicates the [table] to retrieve data from.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/From_(SQL))
 *
 * @sample kuery.clauses.ClausesTest.selectAll
 */
fun Query.from(table: String) = queryOf("$this ${From(table)}")

/**
 * The WHERE clause includes a comparison [predicate], which restricts the rows returned by the query.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/Where_(SQL))
 *
 * @sample kuery.clauses.ClausesTest.where
 */
fun Query.where(predicate: Predicate) =  queryOf("$this ${Where(predicate)}", *predicate.args)

/**
 * The GROUP BY clause projects rows having common values into a smaller set of rows.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/Group_by_(SQL))
 *
 * @sample kuery.clauses.ClausesTest.groupBy
 */
fun Query.groupBy(vararg columns: Any) = queryOf("$this ${GroupBy(columns)}", *this.args)

/**
 * The ORDER BY clause identifies which [columns] to use to sort the resulting data, and in which direction to sort them.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/Order_by_(SQL))
 *
 * @sample kuery.clauses.ClausesTest.orderBy
 */
fun Query.orderBy(vararg columns: Any) = queryOf("$this ${OrderBy(columns)}", *this.args)

/**
 * Returns a sorting where [this] ASC.
 *
 * @sample kuery.clauses.ClausesTest.orderBy
 */
fun String.asc() = "$this ASC"

/**
 * Returns a sorting where [this] DESC.
 *
 * @sample kuery.clauses.ClausesTest.orderBy
 */
fun String.desc() = "$this DESC"

/**
 * The OFFSET clause specifies the number of [rows] to skip before starting to return data.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/SQL_syntax)
 *
 * @sample kuery.clauses.ClausesTest.offset
 */
fun Query.offset(rows: Long) = queryOf("$this ${Offset(rows)}", *this.args)

/**
 * The LIMIT clause specifies the number of [rows] to return.
 *
 * **See:** [Wikipedia](https://en.wikipedia.org/wiki/SQL_syntax)
 *
 * @sample kuery.clauses.ClausesTest.limit
 */
fun Query.limit(rows: Int) = queryOf("$this ${Limit(rows)}", *this.args)