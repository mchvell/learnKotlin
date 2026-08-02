package moduleThree

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class DiscountTypeTest {

    @Test
    fun applyNoneDiscount() {
        val expected = 30000
        val result = DiscountType.NONE.apply(30000, 20)
        assertEquals(expected, result)
    }

    @Test
    fun applyPercentDiscount() {
        val expected = 9000
        val result = DiscountType.PERCENT.apply(10000, 10)
        assertEquals(expected, result)
    }

    @Test
    fun applyFixedDiscount() {
        val expected = 3000
        val result = DiscountType.FIXED.apply(4000, 1000)
        assertEquals(expected, result)
    }

    @Test
    fun applyFixedDiscountBiggerThanPrice() {
        val expected = 0
        val result = DiscountType.FIXED.apply(4000, 5000)
        assertEquals(expected, result)
    }

    @Test
    fun applyHalfDiscount() {
        val expected = 4500
        val result = DiscountType.HALF.apply(9000, 10)
        assertEquals(expected, result)
    }

    @Test
    fun describeHalfDiscount() {
        val expected = "Половина цены"
        val result = describeDiscount(DiscountType.HALF, 40)
        assertEquals(expected, result)
    }

    @Test
    fun describePercentDiscount() {
        val expected = "Процентная (20)"
        val result = describeDiscount(DiscountType.PERCENT, 20)
        assertEquals(expected, result)
    }

    @Test
    fun parseLowerCaseWithSpace() {
        val expected = DiscountType.NONE
        val result = parseType(" none")
        assertEquals(expected, result)
    }

    @Test
    fun parseNullType() {
        val result = parseType(null)
        assertNull(result)
    }

    @Test
    fun parseUnexpectedType() {
        val result = parseType(" DOTA2")
        assertNull(result)
    }

    @Test
    fun typesWithoutValueIgnoreIt() {
        val types = DiscountType.entries
        for (type in types) {
            if (!type.needsValue) {
                val withZero = type.apply(10000, 0)
                val withOther = type.apply(10000, 999)
                assertEquals(withZero, withOther, "тип $type: результат зависит от value")
            }
        }
    }

}