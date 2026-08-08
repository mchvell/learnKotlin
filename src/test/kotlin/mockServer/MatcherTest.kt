package mockServer

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class MatcherTest {

    @Test
    fun exactMatch() {
        val result = ExactMatcher("/api/v2/orders").matches("/api/v2/orders")
        assertTrue(result, "matches() вернул $result, а должен был true")
    }

    @Test
    fun notMatchExactly() {
        val result = ExactMatcher("/api/v2/orders").matches("/pay")
        assertFalse(result, "matches() вернул $result, а должен был false")
    }

    @Test
    fun exactMatchFailsWithIllegalArguments() {
        assertFailsWith<IllegalArgumentException> {
            ExactMatcher(" ")
        }
    }

    @Test
    fun prefixMatch() {
        val result = PrefixMatcher("/api").matches("/api/v2/orders")
        assertTrue(result)
    }

    @Test
    fun notPrefixMatch() {
        val result = PrefixMatcher("/api/v2").matches("/api/v1/orders")
        assertFalse(result)
    }

    @Test
    fun prefixMatchFailsWithIllegalArguments() {
        assertFailsWith<IllegalArgumentException> {
            PrefixMatcher("")
        }
    }
}