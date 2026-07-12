package moduleThree

import jdk.javadoc.internal.doclets.toolkit.taglets.snippet.Style

/*
1. Полиморфный Calculator (ядро v2 — руками)
База: open class PriceModifier(val name: String)
  с open fun apply(price: Int): Int = price   (заглушка «без изменений»)
Наследники со СВОИМИ apply (require на percent — сам знаешь):
  MarkupModifier(percent)   : "Наценка",  +percent%
  DiscountModifier(percent) : "Скидка",   -percent%
В main: val modifiers = listOf(MarkupModifier(15), DiscountModifier(20))
Прогони price = 1000 через цикл for (m in modifiers) { price = m.apply(price) }
Контроль: 1000 → 1150 → 920. ВАЖНО: предскажи оба числа ДО запуска.
*/

open class PriceModifierNew(val name: String){
    init {
        require(name.isNotBlank()) {"Название модификатора не может быть пустым, $name"}
    }
    open fun apply(price: Int) = price
}

class MarkupModifierNew(val percent: Int): PriceModifierNew("Наценка"){
    init {
        require(percent in 0..100){"Наценка должна быть в диапозоне 0-100, $percent"}
    }
    override fun apply(price: Int) = price + price * percent / 100
}

class DiscountModifier(val percent: Int): PriceModifierNew("Скидка"){
    init {
        require(percent in 0..100){"Скидка должна быть в диапозоне 0-100, $percent"}
    }
    override fun apply(price: Int) = price - price * percent / 100
}

/*
2. Экраны с super (паттерн автотестов)
open class BaseScreen(val screenName: String) {
    open fun open(): String = "[$screenName] загрузка базовых элементов"
}
class CheckoutScreen : BaseScreen("Чекаут") — override fun open():
  вернуть строку из ДВУХ строк (super + "\n" + "[Чекаут] ожидание кнопки оплаты")
class SplashScreen : BaseScreen("Сплэш") — override fun open():
  ПОЛНОСТЬЮ своя строка "[Сплэш] мгновенный показ", БЕЗ super — осознанная замена
Вопрос после кода: в каком из двух наследников потеря super была бы багом и почему?
*/

open class BaseScreen(val screenName: String) {
    init {
        require(screenName.isNotBlank()){"У экрана должно быть название, $screenName"}
    }
    open fun open(): String = "[$screenName] загрузка базовых элементов"
}

class CheckoutScreen: BaseScreen("Чекаут"){
    override fun open(): String {
        return (super.open() + "\n" + "[$screenName] ожидание кнопки оплаты")
    }
}

class SplashScreen: BaseScreen("Сплэш"){
    override fun open(): String = "[$screenName] мгновенный показ"
}


fun main(){
    val modifiers = listOf(MarkupModifierNew(15), DiscountModifier(20))
    var price = 1000
    // применение модификаторов идет в цикле, те сначала мы примени наценку, затем к цене с наценкой скидку
    for (m in modifiers) {
        price = m.apply(price)
        println(price)
    }

    println(CheckoutScreen().open())
    println(SplashScreen().open())

}

