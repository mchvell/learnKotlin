package mockServer

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class ExtensionTest {

    @Test
    fun summaryWithBody() {
        val result = MockRequest(HttpMethod.POST, "/v3/orders", """result: 1""").summary()
        val expected = "метод: POST путь: /v3/orders"
        assertEquals(expected, result)
    }

    @Test
    fun summaryWithEmptyBody() {
        val result = MockRequest(HttpMethod.GET, "/v1/card", null).summary()
        val expected = "метод: GET путь: /v1/card"
        assertEquals(expected, result)
    }

    @Test
    fun isServerError() {
        val statuses = listOf(500, 599)
        for (status in statuses) {
            assertTrue(MockResponse(status).isServerError)
        }
    }

    @Test
    fun isNotServerError() {
        assertFalse(MockResponse(499).isServerError)
    }

    @Test
    fun addAll() {
        val mockGet = MockRule(ExactMatcher("/v3/orders"), HttpMethod.GET, MockResponse(200, "OK"))
        val mockPost = MockRule(ExactMatcher("/v2/delivery"), HttpMethod.POST, MockResponse(200, "OK"))
        val listOfMocks = listOf(mockGet, mockPost)
        val registry = MockRegistry().apply { addAll(listOfMocks) }
        assertEquals(2, registry.rulesCount)
    }

    @Test
    fun addAllWithOrder() {
        val firstRule = MockRule(ExactMatcher("/v3/orders"), HttpMethod.GET, MockResponse(200, "First"))
        val secondRule = MockRule(ExactMatcher("/v3/orders"), HttpMethod.GET, MockResponse(200, "Second"))
        val listOfMocks = listOf(firstRule, secondRule)
        val registry = MockRegistry().apply { addAll(listOfMocks) }
        val request = MockRequest(HttpMethod.GET, "/v3/orders", null)

        assertEquals("First", registry.respondTo(request).body)
    }

}
