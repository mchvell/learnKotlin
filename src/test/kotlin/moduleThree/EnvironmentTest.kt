package moduleThree

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull


class EnvironmentTest {

    @Test
    fun endpointCreation() {
        val url = endpoint(Environment.PROD, "/cart")
        val expected = "https://api.wb.ru/cart"
        assertEquals(expected, url)
    }

    @Test
    fun parseUnknownString() {
        val result = parse("unknown")
        assertNull(result)
    }

    @Test
    fun parseNull() {
        val result = parse(null)
        assertNull(result)
    }

    @Test
    fun guardList() {
        val items = listOf(
            guard(Environment.DEV),
            guard(Environment.STAGE),
            guard(Environment.PROD)
        )

        val results = listOf(
            "Среда: Dev, возможны ошибки",
            "Среда: Stage, возможны новые версии методов",
            "Среда: Prod, возможна работа анти-бота"
        )

        assertEquals(results, items)
    }

    @Test
    fun allowsDestructiveIsProd() {
        val environments = Environment.entries
        for (env in environments) {
            if(env.isProduction()) {
                assertFalse { env.allowsDestructiveTests }
            }
        }
    }
}

