package moduleFour

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.json.Json


class SerializableTest {


    @Test
    fun objectsAreEqual() {
        val raw = """{"id": 1, "name": "Кофе", "price_kopecks": 49900, "discount": 10}"""
        val result = Json.decodeFromString<Product>(raw)
        val expected = Product(1, "Кофе", 49900, 10)
        assertEquals(expected, result)
    }

    @Test
    fun objectsWithOutDiscount() {
        val raw = """{"id": 2, "name": "Мяч", "price_kopecks": 49900}"""
        val result = Json.decodeFromString<Product>(raw)
        val expected = Product(2, "Мяч", 49900)
        assertEquals(expected, result)
    }

    @Test
    fun objectWithMoreKeys() {
        val json = Json {ignoreUnknownKeys = true}
        val raw = """{"id": 3, "name": "Вино", "price_kopecks": 149900, "discount": 10, "isPromo": true}"""
        val result = json.decodeFromString<Product>(raw)
        val expected = Product(3, "Вино", 149900, 10)
        assertEquals(expected, result)
    }
}