package kuery.postgres

import kuery.types.predicateOf

/**
 * Returns a predicate where [this] @@ [value].
 *
 * @sample kuery.postgres.OperatorsTest.match
 */
infix fun String.match(value: Any) = predicateOf("$this @@ ?" , value)

/**
 * Returns a predicate where [this] @> {[value]}.
 *
 * @sample kuery.postgres.OperatorsTest.match
 */
infix fun String.contains(value: Any) = predicateOf("$this @> {?}" , value)

/**
 * Returns a predicate where NOT [this] @> {[value]}.
 *
 * @sample kuery.postgres.OperatorsTest.match
 */
infix fun String.notContains(value: Any) = predicateOf("NOT ($this @> {?})" , value)