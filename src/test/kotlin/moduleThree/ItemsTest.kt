package moduleThree

import repeatPractise.Book
import repeatPractise.Discountable
import repeatPractise.Fridge
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse


class ItemsTest {

    @Test
    fun finalPriceOfBook() {
        val result = Book(100000, 250).finalPrice()
        assertEquals(90000, result)
    }

    @Test
    fun finalPriceForExpensiveFridge() {
        val result = Fridge(200000, 60000).finalPrice()
        assertEquals(190000, result)
    }

    @Test
    fun finalPriceForCheapFridge() {
        val result = Fridge(60000, 60000).finalPrice()
        assertEquals(60000, result)
    }

    @Test
    fun heavyItemIsDetected() {
        val result = Book(100000, 5001).isHeavy()
        assertTrue(result)
    }

    @Test
    fun heavyItemIsNotDetected() {
        val result = Book(100000, 5000).isHeavy()
        assertFalse(result)
    }

    @Test
    fun eachProductAppliesOwnDiscount() {
        val items: List<Discountable> = listOf(
            Book(10000, 5001),
            Fridge(200000, 5002)
        )

        val result : MutableList<Int> = mutableListOf()
        for (item in items) {
            result.add(item.finalPrice())
        }
        val expected = listOf(9000, 190000)
        assertEquals(expected, result)
    }
}