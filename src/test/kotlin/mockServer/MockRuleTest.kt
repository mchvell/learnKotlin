package mockServer

import kotlin.test.Test

import kotlin.test.assertTrue
import kotlin.test.assertFalse

class MockRuleTest {

    @Test
    fun getMatchMockRule() {

        val request = MockRequest(HttpMethod.GET, "/api/v2/orders", null)
        val response = MockResponse(200, "OK")
        val result = MockRule.get("/api/v2/orders", response).matches(request)

        assertTrue(result)
    }

    @Test
    fun getMissMatchMockRule() {
        val request = MockRequest(HttpMethod.GET, "/api/v1/orders", null)
        val response = MockResponse(200, "")
        val result = MockRule.get("/api/v2/orders", response).matches(request)

        assertFalse(result)
    }

    @Test
    fun postMatchMockRule() {
        val request = MockRequest(HttpMethod.POST, "/v5/order", """{"orderId": 111}""")
        val response = MockResponse(200, """{"state": 0}""")
        val result = MockRule.post("/v5/order", response).matches(request)

        assertTrue(result)
    }

    @Test
    fun ruleDoesNotMatchDifferentMethod() {
        val request = MockRequest(HttpMethod.POST, "/v5/order", """{"orderId": 111}""")
        val response = MockResponse(200, """{"state": 0}""")
        val result = MockRule.post("/v4/order", response).matches(request)

        assertFalse(result)
    }

    @Test
    fun putMatchMockRule() {
        val request = MockRequest(HttpMethod.PUT, "/v3/profile", null)
        val response = MockResponse(200, """{"state": 0}""")
        val result = MockRule.post("/v3/profile", response).matches(request)

        assertFalse(result)
    }

    @Test
    fun ruleWorksWithAnyMatcher() {
        val alwaysTrue = object : PathMatcher {
            override fun matches(path: String) = true
        }

        val response = MockResponse(200, "OK")
        val request = MockRequest(HttpMethod.GET, "/whatever", null)
        val rule = MockRule(alwaysTrue, HttpMethod.GET, response)

        assertTrue(rule.matches(request))
    }
}