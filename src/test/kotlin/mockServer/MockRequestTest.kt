package mockServer

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertEquals

class MockRequestTest {

    @Test
    fun pathWithoutSlash() {
        assertFailsWith<IllegalArgumentException> {
            MockRequest(HttpMethod.GET, "path", null)
        }
    }

    @Test
    fun pathIsEmpty() {
        assertFailsWith<IllegalArgumentException> {
            MockRequest(HttpMethod.PUT, "", null)
        }
    }

    @Test
    fun validPath() {
        val result = MockRequest(HttpMethod.GET, "/gateway", null).path
        assertEquals("/gateway", result)
    }

    @Test
    fun requestWithNoBody() {
        val result = MockRequest(HttpMethod.POST, "/postpaid", null).hasBody
        assertFalse(result, "Ожидалось не null в теле заказа")
    }

    @Test
    fun requestWithEmptyBody() {
        val result = MockRequest(HttpMethod.POST, "/postpaid", "").hasBody
        assertFalse(result, "Ожидалось не пустое тело заказа")
    }

    @Test
    fun requestWithBody() {

        val result = MockRequest(HttpMethod.POST, "/postpaid", """{"key":"value"}"""").body
        assertEquals("""{"key":"value"}""", result)
    }

    @Test
    fun requestHasBody() {
        val result = MockRequest(HttpMethod.POST, "/postpaid", """{"key":"value"}""").hasBody
        assertTrue(result)
    }

}