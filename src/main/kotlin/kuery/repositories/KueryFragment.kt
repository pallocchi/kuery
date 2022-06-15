package kuery.repositories

import kuery.types.Predicate

fun <T> KueryFragment<T>.findOneOrNull(predicate: Predicate): T? = findOne(predicate).orElse(null)