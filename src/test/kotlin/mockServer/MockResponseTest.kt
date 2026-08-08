package mockServer

import mockServer.MockResponse.Companion.NOT_FOUND
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import kotlin.test.assertFalse


class MockResponseTest {

    @Test
    fun responseValidStatuses() {
        val statuses = listOf(100, 599)
        for (status in statuses) {
            assertEquals(status, MockResponse(status).code)
        }
    }

    @Test
    fun responseInvalidStatuses() {
        val statuses = listOf(99, 600)
        for (status in statuses) {
            assertFailsWith<IllegalArgumentException> {
                MockResponse(status).code
            }
        }
    }

    @Test
    fun successResponses() {
        for (status in 200..299) {
            assertTrue(MockResponse(status).isSuccess)
        }
    }

    @Test
    fun unsuccessfulResponses() {
        val statuses = listOf(199, 300)
        for (status in statuses) {
            assertFalse(MockResponse(status).isSuccess)
        }
    }

    @Test
    fun okCreateValidResponse() {
        val result = MockResponse.ok("ok")
        assertEquals(MockResponse(200, "ok"), result)
    }

    @Test
    fun notFoundCode() {
        val result = NOT_FOUND.code
        assertEquals(404, result)
    }

    @Test
    fun notFound() {
        val result = NOT_FOUND
        assertEquals(MockResponse(404, "not found"), result)
    }
}
