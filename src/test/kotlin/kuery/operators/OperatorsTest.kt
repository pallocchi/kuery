package kuery.operators

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OperatorsTest {

    @Test
    fun eq() {
        val predicate = "name" eq "ross"
        assertThat(predicate.toString()).isEqualTo("name = ?")
        assertThat(predicate.args).containsExactly("ross")
    }

    @Test
    fun neq() {
        val predicate = "name" neq "chandler"
        assertThat(predicate.toString()).isEqualTo("name != ?")
        assertThat(predicate.args).containsExactly("chandler")
    }

    @Test
    fun gt() {
        val predicate = "age" gt 21
        assertThat(predicate.toString()).isEqualTo("age > ?")
        assertThat(predicate.args).containsExactly(21)
    }

    @Test
    fun gte() {
        val predicate = "age" gte 21
        assertThat(predicate.toString()).isEqualTo("age >= ?")
        assertThat(predicate.args).containsExactly(21)
    }

    @Test
    fun lt() {
        val predicate = "age" lt 99
        assertThat(predicate.toString()).isEqualTo("age < ?")
        assertThat(predicate.args).containsExactly(99)
    }

    @Test
    fun lte() {
        val predicate = "age" lte 99
        assertThat(predicate.toString()).isEqualTo("age <= ?")
        assertThat(predicate.args).containsExactly(99)
    }

    @Test
    fun like() {
        val predicate = "name" like "joey"
        assertThat(predicate.toString()).isEqualTo("name LIKE ?")
        assertThat(predicate.args).containsExactly("joey")
    }

    @Test
    fun notLike() {
        val predicate = "name" notLike "joey"
        assertThat(predicate.toString()).isEqualTo("name NOT LIKE ?")
        assertThat(predicate.args).containsExactly("joey")
    }

    @Test
    fun between() {
        val predicate = "age" between Pair(21, 99)
        assertThat(predicate.toString()).isEqualTo("age BETWEEN ? AND ?")
        assertThat(predicate.args).containsExactly(21, 99)
    }

    @Test
    fun isNull() {
        val predicate = "age".isNull()
        assertThat(predicate.toString()).isEqualTo("age IS NULL")
        assertThat(predicate.args).isEmpty()
    }

    @Test
    fun isNotNull() {
        val predicate = "age".isNotNull()
        assertThat(predicate.toString()).isEqualTo("age IS NOT NULL")
        assertThat(predicate.args).isEmpty()
    }

    @Test
    fun inList() {
        val predicate = "color" inList arrayOf("red", "blue")
        assertThat(predicate.toString()).isEqualTo("color IN (?,?)")
        assertThat(predicate.args).containsExactly("red", "blue")
    }

    @Test
    fun isTrue() {
        val predicate = "active".isTrue()
        assertThat(predicate.toString()).isEqualTo("active IS TRUE")
        assertThat(predicate.args).isEmpty()
    }

    @Test
    fun isFalse() {
        val predicate = "active".isFalse()
        assertThat(predicate.toString()).isEqualTo("active IS FALSE")
        assertThat(predicate.args).isEmpty()
    }
}