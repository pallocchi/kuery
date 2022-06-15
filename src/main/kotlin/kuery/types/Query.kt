package kuery.types

/**
 * Returns a query for the given [statement].
 */
fun queryOf(statement: String) = Query(statement, emptyArray())

/**
 * Returns a query for the given [statement].
 */
fun queryOf(statement: String, vararg args: Any) = Query(statement, args)

/**
 * A root query instance.
 */
class Query(
    val statement: String,
    val args: Array<out Any>,
) {
    override fun toString() = statement
}