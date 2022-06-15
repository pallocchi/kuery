package kuery.predicates

import kuery.operators.eq
import kuery.types.predicateOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PredicatesTest {

    @Test
    fun create() {
        val predicate = predicateOf("TRUE")
        assertThat(predicate.toString()).isEqualTo("TRUE")
        assertThat(predicate.args).isEmpty()
    }

    @Test
    fun and() {
        val predicate = ("age" eq 21) and ("name" eq "ross")
        assertThat(predicate.toString()).isEqualTo("age = ? AND name = ?")
        assertThat(predicate.args).containsExactly(21, "ross")
    }

    @Test
    fun or() {
        val predicate = ("age" eq 21) or ("name" eq "ross")
        assertThat(predicate.toString()).isEqualTo("age = ? OR name = ?")
        assertThat(predicate.args).containsExactly(21, "ross")
    }
}