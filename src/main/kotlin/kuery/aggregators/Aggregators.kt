package kuery.aggregators

/**
 * Returns an aggregator SUM([field]).
 *
 * @sample kuery.aggregators.AggregatorsTest.sum
 */
fun sum(field: String) = agg("SUM", field)

/**
 * Returns an aggregator COUNT([field]).
 *
 * @sample kuery.aggregators.AggregatorsTest.count
 */
fun count(field: String) = agg("COUNT", field)

/**
 * Returns an aggregator COUNT(*).
 *
 * @sample kuery.aggregators.AggregatorsTest.count
 */
fun count() = agg("COUNT", "*")

/**
 * Returns an aggregator AVG([field]).
 *
 * @sample kuery.aggregators.AggregatorsTest.avg
 */
fun avg(field: String) = agg("AVG", field)

/**
 * Returns an aggregator MIN([field]).
 *
 * @sample kuery.aggregators.AggregatorsTest.min
 */
fun min(field: String) = agg("MIN", field)

/**
 * Returns an aggregator MAX([field]).
 *
 * @sample kuery.aggregators.AggregatorsTest.max
 */
fun max(field: String) = agg("MAX", field)

/**
 * Returns an aggregator.
 *
 * @sample kuery.aggregators.AggregatorsTest.agg
 */
fun agg(name: String, field: String) = "$name($field)"