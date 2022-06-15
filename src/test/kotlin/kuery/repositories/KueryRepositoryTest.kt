package kuery.repositories

import kuery.operators.eq
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.annotation.Id
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.test.context.TestConstructor

data class Person(
    @Id val id: Int? = null,
    val name: String,
)

interface PersonRepository : KueryRepository<Person, Long>

@EnableKueryRepositories
@SpringBootApplication
open class Main {
    fun main(args: Array<String>) {
        SpringApplicationBuilder(Main::class.java).run(*args)
    }
}

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = [Main::class])
class KueryRepositoryTest(
    private val repository: PersonRepository
) {
    @AfterEach
    fun clearRepositories() {
        repository.deleteAll()
    }

    @Test
    fun shouldFindOneWithPredicate() {

        // given
        val ross: Person = givenSavedPerson("Ross")

        val actual = repository.findOneOrNull("name" eq "Ross")

        assertThat(actual).isEqualTo(ross)
    }

    @Test
    fun shouldFindAllWithPredicate() {

        // given
        val ross: Person = givenSavedPerson("Ross")
        val joey: Person = givenSavedPerson("Joey")

        val actual = repository.findAll("name" eq "Ross")

        assertThat(actual).containsOnly(ross)
        assertThat(actual).doesNotContain(joey)
    }

    @Test
    fun shouldFindAllWithPredicateAndPage() {

        // given
        val ross: Person = givenSavedPerson("Ross")
        val joey: Person = givenSavedPerson("Joey")

        val actual = repository.findAll("name" eq "Ross", Pageable.ofSize(1))

        assertThat(actual).containsOnly(ross)
        assertThat(actual).doesNotContain(joey)
        assertThat(actual.totalPages).isEqualTo(1)
        assertThat(actual.totalElements).isEqualTo(1)
    }

    @Test
    fun shouldFindAllWithPredicateAndSorting() {

        // given
        val ross: Person = givenSavedPerson("Ross")
        val joey: Person = givenSavedPerson("Joey")
        val page =  PageRequest.of(0, 10, Sort.by("name").descending())

        val actual = repository.findAll("name" eq "Ross", page)

        assertThat(actual).containsOnly(ross)
        assertThat(actual).doesNotContain(joey)
        assertThat(actual.totalPages).isEqualTo(1)
        assertThat(actual.totalElements).isEqualTo(1)
    }

    private fun givenSavedPerson(name: String): Person {
        return repository.save(Person(name = name))
    }
}