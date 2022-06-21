package kuery.postgres

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class OperatorsTest {

    @Test
    fun match() {
        val predicate = "name" match "ross"
        Assertions.assertThat(predicate.toString()).isEqualTo("name @@ ?")
        Assertions.assertThat(predicate.args).containsExactly("ross")
    }
}