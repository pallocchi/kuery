package kuery.clauses

import kuery.operators.eq
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ClausesTest {

    @Test
    fun selectAll() {
        val query = select().from("user")
        assertThat(query.toString()).isEqualTo("SELECT * FROM user")
    }

    @Test
    fun selectCol() {
        val query = select("id", "name").from("user")
        assertThat(query.toString()).isEqualTo("SELECT id, name FROM user")
    }

    @Test
    fun where() {
        val query = select().from("user").where("id" eq  2)
        assertThat(query.toString()).isEqualTo("SELECT * FROM user WHERE id = ?")
    }

    @Test
    fun groupBy() {
        val query = select().from("user").groupBy("id")
        assertThat(query.toString()).isEqualTo("SELECT * FROM user GROUP BY id")
    }

    @Test
    fun orderBy() {
        val query = select().from("user").orderBy("id".asc(), "date".desc())
        assertThat(query.toString()).isEqualTo("SELECT * FROM user ORDER BY id ASC, date DESC")
    }

    @Test
    fun offset() {
        val query = select().from("user").offset(1)
        assertThat(query.toString()).isEqualTo("SELECT * FROM user OFFSET 1")
    }

    @Test
    fun limit() {
        val query = select().from("user").limit(1)
        assertThat(query.toString()).isEqualTo("SELECT * FROM user LIMIT 1")
    }
}