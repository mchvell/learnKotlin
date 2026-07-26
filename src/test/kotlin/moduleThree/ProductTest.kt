package moduleThree

import repeatPractise.ArtBook
import repeatPractise.Freezer
import repeatPractise.Product
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ProductTest {

    @Test
    fun bookDescription() {
        val result = ArtBook("Процесс", 35000, "Ф. Кафка").description()
        val expected = "Процесс – 350 ₽, автор: Ф. Кафка"
        assertEquals(expected, result)
    }

    @Test
    fun freezerDescription() {
        val result = Freezer("Бирюза", 990000, 50000).description()
        val expected = "Бирюза – 9900 ₽"
        assertEquals(expected, result)
    }

    @Test
    fun priceRoublesWithKopecks() {
        val result = Freezer("Атлант", 10045, 50000).priceRubles
        assertEquals(100, result)
    }

    @Test
    fun failOnZeroPrice() {
        assertFailsWith<IllegalArgumentException> {
            Freezer("Вирпул", 0, 50000)
        }
    }

    @Test
    fun failOnEmptyName() {
        assertFailsWith<IllegalArgumentException> {
            Freezer(" ", 100000, 50000)
        }
    }

    @Test
    fun failOnNegativeWeight() {
        assertFailsWith<IllegalArgumentException> {
            Freezer("Замок", 100000, -1)
        }
    }

    @Test
    fun chainCategory() {
        val items: List<Product> = listOf(
            ArtBook("Калигула", 20000, "Камю"),
            Freezer("Индезит", 1900000, 70000)
        )
        val expected: List<String> = listOf("Книги", "Техника")
        val result : MutableList<String> = mutableListOf()

        for (item in items) {
            result.add(item.category())
        }

        assertEquals(expected, result)
    }
}