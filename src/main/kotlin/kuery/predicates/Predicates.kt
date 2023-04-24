package kuery.predicates

import kuery.types.Predicate
import kuery.types.predicateOf

/**
 * Returns a new predicate which statement is [this] AND [that].
 *
 * @sample kuery.predicates.PredicatesTest.and
 */
infix fun Predicate.and(that: Predicate) = predicateOf("($this AND $that)", *this.args, *that.args)

/**
 * Returns a new predicate which statement is [this] OR [that].
 *
 * @sample kuery.predicates.PredicatesTest.or
 */
infix fun Predicate.or(that: Predicate) = predicateOf("($this OR $that)", *this.args, *that.args)