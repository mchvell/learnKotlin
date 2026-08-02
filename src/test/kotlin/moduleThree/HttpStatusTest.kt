package moduleThree

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class HttpStatusTest {

    @Test
    fun boundaryBetweenInformationalAndSuccess() {
        val result = listOf(categoryOf(199), categoryOf(200))
        val expected = listOf(HttpStatusCategory.INFORMATIONAL, HttpStatusCategory.SUCCESS)
        assertEquals(expected, result)
    }

    @Test
    fun codesAbove599AreUnknown() {
        val result = listOf(categoryOf(599), categoryOf(600))
        val expected = listOf(HttpStatusCategory.SERVER_ERROR, null)
        assertEquals(expected, result)
    }

    @Test
    fun describeForStatusCode() {
        val result = describe(200)
        val expected = "200 — Успех"
        assertEquals(expected, result)
    }

    @Test
    fun describeForUnknownStatusCode() {
        val result = describe(999)
        val expected = "999 — неизвестный код"
        assertEquals(expected, result)
    }

    @Test
    fun retryableCategoriesAreErrors() {
        val statuses = HttpStatusCategory.entries
        for (status in statuses) {
            if (status.isRetryable()) {
                assertTrue("категория $status: retryable, но не error") { status.isError }
            }
        }
    }

    @Test
    fun shouldRetryForClientError() {
        val code = 400
        val result = shouldRetry(code)
        assertFalse("$code ретраится, хотя не должен") { result }
    }

    @Test
    fun shouldRetryForServerError() {
        val code = 502
        val result = shouldRetry(code)
        assertTrue("$code не ретраится, хотя должен") { result }
    }

}