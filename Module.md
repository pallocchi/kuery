# Module Kuery

A dynamic SQL executor for [Spring Data JDBC](https://docs.spring.io/spring-data/jdbc/docs/current/reference/html) and [Kotlin](https://kotlinlang.org).

```
// 1. Declare the entity
data class Person(val name: String)

// 2. Declare the repository
interface PersonRepository : KueryRepository<Person>

// 3. Execute the query
val people = repository.findBy("name" eq "ross")
```

# Package kuery.types

Contains the Kuery types, such `Query` or `Predicate`.

# Package kuery.clauses

Contains the SQL clauses, such `select`, `from`, `orderBy`.

# Package kuery.operators

Contains the SQL operators, such `eq`, `gt`, `like`.

# Package kuery.predicates

Contains the SQL logical operators, such `and`, `or`.

# Package kuery.aggregators

Contains the SQL aggregators, such `count`, `min`, `max`.