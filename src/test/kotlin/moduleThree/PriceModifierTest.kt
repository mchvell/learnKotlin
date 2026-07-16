package moduleThree

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertEquals


class TestDataForPriceModifier{
    val priceWithMarkup = 105
    val priceWithDiscount = 95
    val priceWithFixedFee = 133

}


class PriceModifierTest {

    // приватная переменная с созданием объекта. нужна для того, чтобы код тестов выглядел лаконичнее
    private val data = TestDataForPriceModifier()

    // Тест на проверку наценки
    @Test
    fun checkMarkupModification(){
        val result = MarkupModifierNew(5).apply(100)
        assertEquals(data.priceWithMarkup, result)
    }

    // Тест на проверку скидки
    @Test
    fun checkDiscountModification(){
        val result = DiscountModifier(5).apply(100)
        assertEquals(data.priceWithDiscount, result)
    }

    // тест на проверку фиксированной комиссии
    @Test
    fun checkFixedFeeModification(){
        val result = FixedFeeModifier(33).apply(100)
        assertEquals(data.priceWithFixedFee, result)
    }

    // проверка цепочки модификаций
    @Test
    fun checkModificationChain(){
        var price = 1000
        val modifiers = listOf(MarkupModifierNew(15), DiscountModifier(20),
            FixedFeeModifier(50))
        for (m in modifiers){
            price = m.apply(price)
        }
        assertEquals(970, price)
    }

    // создание наценки с невалидным значением невозможно
    @Test
    fun checkFailedToCreateMarkupWithWrongParameter(){
        assertFailsWith<IllegalArgumentException> {
            MarkupModifierNew(-1)
        }
    }

    // создание скидки с невалидным значением невозможно
    @Test
    fun checkFailedToCreateDiscountWithWrongParameter(){
        assertFailsWith<IllegalArgumentException> {
            DiscountModifier(101)
        }
    }

    // создание комисии с невалидным значением невозможно
    @Test
    fun checkFailedToCreateFixedFeeWithWrongParameter(){
        assertFailsWith<IllegalArgumentException> {
            FixedFeeModifier(-99)
        }
    }

    @Test
    fun checkDescribe(){
        val result = MarkupModifierNew(5).describe()
        assertEquals("Модификатор: Наценка", result)
    }

}