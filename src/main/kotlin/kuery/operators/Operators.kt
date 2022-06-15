package kuery.operators

import kuery.types.predicateOf
import kuery.utilities.toWildcards

/**
 * Returns a predicate where [this] = [value].
 *
 * @sample kuery.operators.OperatorsTest.eq
 */
infix fun String.eq(value: Any) = predicateOf("$this = ?" , value)

/**
 * Returns a predicate where [this] != [value].
 *
 * @sample kuery.operators.OperatorsTest.neq
 */
infix fun String.neq(value: Any) = predicateOf("$this != ?" , value)

/**
 * Returns a predicate where [this] > [value].
 *
 * @sample kuery.operators.OperatorsTest.gt
 */
infix fun String.gt(value: Any) = predicateOf("$this > ?" , value)

/**
 * Returns a predicate where [this] >= [value].
 *
 * @sample kuery.operators.OperatorsTest.gte
 */
infix fun String.gte(value: Any) = predicateOf("$this >= ?" , value)

/**
 * Returns a predicate where [this] < [value].
 *
 * @sample kuery.operators.OperatorsTest.lt
 */
infix fun String.lt(value: Any) = predicateOf("$this < ?" , value)

/**
 * Returns a predicate where [this] <= [value].
 *
 * @sample kuery.operators.OperatorsTest.lte
 */
infix fun String.lte(value: Any) = predicateOf("$this <= ?" , value)

/**
 * Returns a predicate where [this] LIKE [value].
 *
 * @sample kuery.operators.OperatorsTest.like
 */
infix fun String.like(value: Any) = predicateOf("$this LIKE ?" , value)

/**
 * Returns a predicate where [this] NOT LIKE [value].
 *
 * @sample kuery.operators.OperatorsTest.notLike
 */
infix fun String.notLike(value: Any) = predicateOf("$this NOT LIKE ?" , value)

/**
 * Returns a predicate where [this] BETWEEN [values].first AND [values].second.
 *
 * @sample kuery.operators.OperatorsTest.between
 */
infix fun String.between(values: Pair<Any, Any>) = predicateOf("$this BETWEEN ? AND ?", values.first, values.second)

/**
 * Returns a predicate where [this] IS NULL.
 *
 * @sample kuery.operators.OperatorsTest.isNull
 */
fun String.isNull() = predicateOf("$this IS NULL")

/**
 * Returns a predicate where [this] IS NOT NULL.
 *
 * @sample kuery.operators.OperatorsTest.isNotNull
 */
fun String.isNotNull() = predicateOf("$this IS NOT NULL")

/**
 * Returns a predicate where [this] IN ([values]).
 *
 * @sample kuery.operators.OperatorsTest.inList
 */
infix fun String.inList(values: Array<Any>) = predicateOf("$this IN ${values.toWildcards()}", *values)

/**
 * Returns a predicate where [this] IN ([values]).
 *
 * @sample kuery.operators.OperatorsTest.inList
 */
infix fun String.inList(values: Collection<Any>) = inList(values.toTypedArray())

/**
 * Returns a predicate where [this] IS TRUE.
 *
 * @sample kuery.operators.OperatorsTest.isTrue
 */
fun String.isTrue() = predicateOf("$this IS TRUE")

/**
 * Returns a predicate where [this] IS FALSE.
 *
 * @sample kuery.operators.OperatorsTest.isFalse
 */
fun String.isFalse() = predicateOf("$this IS FALSE")