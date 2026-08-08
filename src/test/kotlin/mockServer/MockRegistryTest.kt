package mockServer

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class MockRegistryTest {

    private fun rulesHelper() : MockRegistry {
        val registry = MockRegistry()
        val rule = MockRule(ExactMatcher("/v3/orders"), HttpMethod.POST, MockResponse(200, "OK-TEST"))
        registry.addRule(rule)
        return registry
    }

    @Test
    fun findResponse() {
        val result = rulesHelper().findResponse(MockRequest(HttpMethod.POST, "/v3/orders", null))
        assertEquals(MockResponse(200, "OK-TEST"), result)
    }

    @Test
    fun countRules() {
        val result = rulesHelper().rulesCount
        assertEquals(1, result)
    }

    @Test
    fun resetCounter() {
        val registry = rulesHelper()
        registry.reset()
        val request = MockRequest(HttpMethod.POST, "/v3/orders", null)

        assertEquals(0, registry.rulesCount)
        assertEquals(MockResponse.NOT_FOUND, registry.respondTo(request))
    }

    @Test
    fun findResponseEmpty() {
        val result = rulesHelper().findResponse(MockRequest(HttpMethod.POST, "/v2/orders", null))
        assertNull(result)
    }

    @Test
    fun respondToNotFound() {
        val result = rulesHelper().respondTo(MockRequest(HttpMethod.POST, "/v2/orders", null))
        assertEquals(MockResponse.NOT_FOUND, result)
    }

    @Test
    fun priorityOfFindResponse() {
        val result = rulesHelper()
        result.addRule(MockRule(ExactMatcher("/v3/orders"), HttpMethod.POST, MockResponse(200, "NOT-OK-TEST")))
        assertEquals("OK-TEST", result.findResponse(MockRequest(HttpMethod.POST, "/v3/orders", null))?.body)

    }
}