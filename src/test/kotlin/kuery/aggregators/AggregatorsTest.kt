package kuery.aggregators

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AggregatorsTest {

    @Test
    fun sum() {
        val agg = sum("total")
        assertThat(agg).isEqualTo("SUM(total)")
    }

    @Test
    fun count() {
        val agg = count("total")
        assertThat(agg).isEqualTo("COUNT(total)")
    }

    @Test
    fun avg() {
        val agg = avg("total")
        assertThat(agg).isEqualTo("AVG(total)")
    }

    @Test
    fun min() {
        val agg = min("total")
        assertThat(agg).isEqualTo("MIN(total)")
    }

    @Test
    fun max() {
        val agg = max("total")
        assertThat(agg).isEqualTo("MAX(total)")
    }

    @Test
    fun agg() {
        val agg = agg("AGG", "total")
        assertThat(agg).isEqualTo("AGG(total)")
    }
}