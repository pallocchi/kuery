package kuery.utilities

/**
 * Returns the wildcards representing [this] array of arguments.
 *
 *  @sample kuery.utilities.ArraysTest.toWildcards
 */
fun Array<*>.toWildcards() = "(${"?,".repeat(size).dropLast(1)})"

/**
 * Returns the wildcards representing [this] array of arguments.
 *
 *  @sample kuery.utilities.ArraysTest.toWildcards
 */
fun Collection<*>.toWildcards() = toTypedArray().toWildcards()