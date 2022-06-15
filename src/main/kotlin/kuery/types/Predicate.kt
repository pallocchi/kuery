package kuery.types

/**
 * Returns a predicate for the given [statement].
 *
 * @sample kuery.predicates.PredicatesTest.create
 */
fun predicateOf(statement: String) = Predicate(statement, emptyArray())

/**
 * Returns a predicate the given [statement] and [args].
 *
 * @sample kuery.predicates.PredicatesTest.create
 */
fun predicateOf(statement: String, vararg args: Any) = Predicate(statement, args)

class Predicate(
    private val statement: String,
    internal val args: Array<out Any>,
) {
    override fun toString() = statement
}