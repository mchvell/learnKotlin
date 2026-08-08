package mockServer

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertNull

class HttpMethodTest {

    @Test
    fun httpMethodIsInvalid() {
        val result = HttpMethod.parseOrNull("waw")
        assertNull(result, "метод не опознан")
    }

    @Test
    fun httpMethodWithWhiteSpaces() {
        val result = HttpMethod.parseOrNull("  get ")
        assertEquals(HttpMethod.GET, result)
    }

    @Test
    fun httpMethodIsNull() {
        val result = HttpMethod.parseOrNull(null)
        assertNull(result, "метод не опознан")

    }

    @Test
    fun defaultHttpMethodIsGet() {
        val result = HttpMethod.DEFAULT
        assertEquals(HttpMethod.GET, result)
    }

    @Test
    fun postCantRead() {
        val result = HttpMethod.POST.isRead()
        assertFalse(result)
    }

    @Test
    fun getCanRead() {
        val result = HttpMethod.GET.isRead()
        assertTrue(result)
    }

    @Test
    fun readMethodsHaveNoBody() {
        val methods = HttpMethod.entries
        for (m in methods) {
            if (m.isRead()) {
                assertFalse(m.hasBody, "метод $m читающий, но помечен как несущий тело")
            }
        }
    }
}