package moduleThree

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertFailsWith


class PaymentScreenTest{

    // Служебный метод, чтобы избежать дублирования в коде
    private fun getResult(): String = PaymentScreen().open()

    // open содержит родительскую загрузку root
    // — доказательство, что super не потеря
    @Test
    fun openContainsRoot(){
        assertContains(getResult(), "жду root")
    }

    // open содержит ожидание payButton
    // — своя часть наследника на месте
    @Test
    fun openContainsPaymentButton(){
        assertContains(getResult(), "жду payButton")
    }

    // open содержит значение таймаута
    // — protected val родителя дочитался
    @Test
    fun openContainsTimeout(){
        assertContains(getResult(), "timeout: 5000")
    }

    //создание экрана с пустым именем падает — BaseScreenNew("...пробелы...")
    // невозможен; тут нужен assertFailsWith (создай наследника прямо... нет, стоп
    //подумай сам, какой объект тут создавать, если имя у PaymentScreen зашито 😉 — это мини-задачка внутри задачи)
    @Test
    fun checkFailCreateWrongNameScree() {
        assertFailsWith<IllegalArgumentException> {
            BaseScreen("   ")
        }
    }
}