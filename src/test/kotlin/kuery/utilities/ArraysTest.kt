package kuery.utilities

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ArraysTest {

    @Test
    fun toWildcards() {
        assertThat(emptyArray<Int>().toWildcards()).isEqualTo("()")
        assertThat(arrayOf(1).toWildcards()).isEqualTo("(?)")
        assertThat(arrayOf(1,2).toWildcards()).isEqualTo("(?,?)")
        assertThat(arrayOf(1,2,3).toWildcards()).isEqualTo("(?,?,?)")
    }
}