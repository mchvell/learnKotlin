package moduleThree

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class DataClassTest {

    private val apple = ProductInfo(107, "Apple", 150, true)
    private val sameApple = ProductInfo(107, "Apple", 150, true)

    @Test
    fun identicalProductsAreEqual() {
        assertEquals(apple, sameApple)
    }

    @Test
    fun copyChangesOnlyRequestedField() {
        val original = ProductInfo(109, "Apple", 135, true)
        val outOfStock = original.copy(inStock = false)

        assertEquals(109, outOfStock.id)
        assertEquals("Apple", outOfStock.name)
        assertEquals(135, outOfStock.price)
        assertFalse(outOfStock.inStock)
        assertTrue(original.inStock)
    }

    @Test
    fun productsWithDifferentPriceAreNotEqual() {
        val cheapPen = ProductInfo(109, "Pen", 20, true)
        val expensivePen = ProductInfo(109, "Pen", 25, true)

        assertNotEquals(cheapPen, expensivePen)
    }

    @Test
    fun listsWithSameProductsAreEqual() {
        val expected = listOf(
            ProductInfo(11, "Tea", 33, true),
            ProductInfo(13, "Vodka", 197, true)
        )
        val actual = listOf(
            ProductInfo(11, "Tea", 33, true),
            ProductInfo(13, "Vodka", 197, true)
        )

        assertEquals(expected, actual)
    }

    @Test
    fun apiResponseWithDifferentBody(){
        val response1 = ApiResponse(200)
        val response2 = ApiResponse(200)
        response2.body = "http error"
        assertEquals(response1, response2)
        println(response2)
    }
}

