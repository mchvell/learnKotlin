package mockServer

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class MockServerFullTest {

    private fun rulesHelper(): MockRegistry {
        val orders = MockRule(ExactMatcher("/v3/orders"), HttpMethod.GET, MockResponse(200, """status: 0"""))
        val buy = MockRule(ExactMatcher("/v1/buy"), HttpMethod.POST, MockResponse(200, """orderId: 1"""))
        val basket = MockRule(ExactMatcher("/v5/basket"), HttpMethod.GET, MockResponse(200, """items: true"""))
        val listOfMocks = listOf(orders, buy, basket)
        val registry = MockRegistry().apply { addAll(listOfMocks) }
        return registry
    }

    @Test
    fun secondRuleAnswersItsRequest() {
        val registry = rulesHelper()
        val request = MockRequest(HttpMethod.POST, "/v1/buy", null)
        val response = registry.respondTo(request)

        with(response) {
            assertEquals(200, code)
            assertEquals("""orderId: 1""", body)
            assertTrue(isSuccess)
        }
    }

    @Test
    fun itemIsNotInRegistry() {
        val registry = rulesHelper()
        val request = MockRequest(HttpMethod.DELETE, "/v1/cancel", null)
        val response = registry.respondTo(request)

       with(response) {
           assertEquals(404, code)
           assertEquals("not found", body)
       }

    }
}