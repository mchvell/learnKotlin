package moduleThree
/*
1. Wallet — компиляция против рантайма (закрываем вопрос 1)
Класс Wallet:
- private var balance: Int (начальный через конструктор, require >= 0)
- fun deposit(amount: Int) — require(amount > 0), увеличивает
- fun withdraw(amount: Int) — require(amount in 1..balance), уменьшает
- fun current(): Int — отдаёт баланс

Твой main должен ДОКАЗАТЬ три разных уровня защиты:
а) рабочий сценарий: депозит + снятие, check на итоговый current()
б) рантайм-защита: withdraw больше баланса → поймай исключение и докажи,
что баланс НЕ изменился (подсказка: try/catch ты ещё не проходил —
поэтому сделай проще: check ДО опасного вызова спрогнозированного
состояния, а опасный вызов оставь последней строкой с комментарием,
ЧТО и ПОЧЕМУ произойдёт)
в) компайл-защита: строка wallet.balance = -50 — напиши её, УБЕДИСЬ,
что IDE подчёркивает, скопируй текст ошибки В КОММЕНТАРИЙ рядом
и закомментируй строку. Это и есть доказательство уровня «не компилируется».

Вопрос после кода: чем защита (в) принципиально сильнее защиты (б)?
Своими словами, две фразы.
*/

class Wallet(balance: Int) {
    private var balance = balance
    init {
        require(balance >=0){"Баланс не должен быть отрицательным, $balance"}
    }
    fun deposit(amount: Int) : Int{
        require(amount > 0){"Сумма взноса должна быть >0, $amount"}
        balance += amount
        return balance
    }

    fun withdraw(amount: Int) : Int{
        require(amount in 1..balance){"Невозможно снять 0 или сумму больше баланса $amount"}
        balance -= amount
        return balance
    }

    fun current(): Int = balance
}

/*
2. TestSuite v2 — реванш (парный инвариант, private set)
  - var totalTests и var failedTests — оба private set
  - init: require на неотрицательность и failedTests <= totalTests
  - fun update(total: Int, failed: Int) — АТОМАРНО меняет оба,
    require на пару ВНУТРИ метода до присваиваний
  - val passRate: Int — вычисляемое, формула из прошлого TestRun
Твой main: докажи check'ами, что (а) рабочий update работает,
(б) кривой update(5, 10) не проходит — тем же приёмом, что в задаче 1б,
(в) прямое suite.failedTests = 999 не компилируется — приёмом 1в.
Вопрос: почему require в update стоит ДО присваиваний — что сломалось бы,
стой он между ними?
*/


class TestSuiteV2(totalTests: Int, failedTests: Int) {
    var totalTests = totalTests
        private set

    var failedTests = failedTests
        private set

    init {
        require(totalTests > 0){"Общее кол-во тестов не может быть равно 0, $totalTests"}
        require(failedTests >= 0){"Кол-во упавших тестов не может быть отрицательным, $failedTests"}
        require(failedTests <= totalTests){"Кол-во упавших тестов не может превышать общее кол-во, упавшие: [$failedTests], " +
                "итого: [$totalTests]"}
    }

    fun update(total: Int, failed: Int) {
        require(total > 0) { "Общее количество тестов должно быть > 0, $total" }
        require(failed >= 0) { "Упавших не может быть меньше нуля, $failed" }
        require(total >= failed){"Общее кол-во тестов не может быть меньше сломанных упавшие: [$failed], итого: [$total]"}
        totalTests = total
        failedTests = failed
    }

    val passRate: Int
        get() = 100 - failedTests * 100 / totalTests
}

/*
3. BaseScreen с protected (главная недобранная тема)
open class BaseScreen(val screenName: String):
  - protected fun waitForElement(id: String): String = "[$screenName] жду $id"
  - protected val defaultTimeout: Int = 5000
  - open fun open(): String — использует waitForElement("root")
class PaymentScreen : BaseScreen("Оплата"):
  - override fun open(): String — super + свой waitForElement("payButton")
    + в строку добавь defaultTimeout (наследник читает protected val родителя!)

Доказательства в main:
  а) check на результат PaymentScreen().open() — все три части строки на месте
  б) пункт (в)-стайл: строка PaymentScreen().waitForElement("x") —
     закомментируй + текст ошибки компилятора в комментарий
Вопрос: ошибка для protected и ошибка для private — одинаковые по МЕХАНИЗМУ
(когда ловятся), но разные по СМЫСЛУ. В чём разница смысла?
*/


open class BaseScreenNew(val screenName: String){
    init {
        require(screenName.isNotBlank()){"Название экрана не может быть пустым, $screenName"}
    }
    protected fun waitForElement(id: String): String{
        require(id.isNotBlank()){"Нельзя передать пустой id элемента,$id"}
        return "[$screenName] жду $id"
    }

    protected val defaultTimeout: Long = 5000

    open fun open(): String = waitForElement("root")
}

class PaymentScreen : BaseScreenNew("Оплата"){
    override fun open(): String{
        val base = super.open()
        val payment = waitForElement("payButton")
        return "$base\n$payment (timeout: $defaultTimeout)"
    }
}


fun main(){
//    val w = Wallet(1500)
//    w.deposit(100)
//    check(w.current()==1600){ "Ожидал 1600, получил ${w.current()}" }
//    println("Проверка прошла")
//
//    check(w.current() == 1600) { "Перед опасным вызовом баланс должен быть 1600" }
//    w.withdraw(1700)

    val result = PaymentScreen().open()
    println(result)

    check(result.contains("жду root")){"потерян super.open()"}
    check(result.contains("жду payButton")) { "Потерян свой вызов waitForElement" }
    check(result.contains("5000")) { "Потерян defaultTimeout" }
    println("PaymentScreen: все части на месте")

}