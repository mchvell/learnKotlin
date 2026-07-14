package moduleThree

/*
1. Разминка: Person → Employee
open class Person(val name: String) — в init: require(name.isNotBlank()) с сообщением
class Employee — наследник: принимает имя и company (val), передаёт имя родителю
Метод info() у Employee — возвращает String "имя (company)"

Контроль:
Employee("Миша", "WB").info() → "Миша (WB)"
Employee("   ", "WB") → УПАДЁТ. Вопрос: чей require его убил — ты этот код
в Employee не писал. Объясни, почему он сработал.
*/

/*
2. Трёхэтажная иерархия: Person → Employee → Manager
К твоим Person и EmployeeNew добавь:
class Manager — наследник EmployeeNew: принимает name, company
и teamSize (val, Int, require > 0 — С СООБЩЕНИЕМ, договор!)
Метод teamInfo() — возвращает "имя (company): команда из N человек"

Но сначала — СТОП. Твой EmployeeNew сейчас открыт для наследования?
Проверь, исправь что нужно, и объясни одним предложением:
почему Person был open «бесплатно», а с EmployeeNew пришлось что-то делать?

Контроль:
Manager("Миша", "WB", 35).teamInfo() → "Миша (WB): команда из 35 человек"
Manager("Миша", "WB", 0) → падает с твоим сообщением
Вопрос после кода: при создании Manager — сколько init-блоков выполнилось
и в каком порядке?
*/

open class Person(val name: String){
    init {
        require(name.isNotBlank()) {"Имя не может быть пустым, $name"}
    }
}

open class EmployeeNew(name: String, val company: String): Person(name){
    init {
        require(company.isNotBlank()){"Название компании обязательно, $company"}
    }
    fun info(): String = "$name ($company)"
}

class Manager(name: String, company: String, val teamSize: Int): EmployeeNew(name, company){
    init {
        require(teamSize > 0){"Размер команды не может быть 0 или отрицательным, $teamSize"}
    }
    fun teamInfo() : String = "$name ($company): команда из $teamSize человек"
}

/*
3. PriceModifier → MarkupModifier (прото-версия Calculator v2!)
open class PriceModifier(val name: String) {
    fun describe(): String = "Модификатор: $name"
}
class MarkupModifier — наследник: принимает percent (val, Int, require in 0..100
с сообщением), name передаёт родителю сам — строкой "Наценка"
(пользователь MarkupModifier имя НЕ передаёт: new MarkupModifier(15) — и всё)
Метод apply(price: Int): Int — цена с наценкой percent

Контроль:
val m = MarkupModifier(15)
m.describe() → "Модификатор: Наценка"   ← метод родителя
m.apply(1000) → 1150                     ← метод наследника
MarkupModifier(150) → падает

Новое в задаче: наследник передаёт родителю НЕ свой параметр,
а фиксированное значение. Подумай, как это записать в строке наследования.
*/

open class PriceModifier(val name: String){
    init {
        require(name.isNotBlank()){"Модификатор не может быть пустым, $name"}
    }
    fun describe(): String = "Модификатор: $name"
}

class MarkupModifier(val percent: Int): PriceModifier("Наценка"){
    init {
        require(percent in 0..100){"Наценка должна быть в срезе от 0 до 100, $percent"}
    }
    fun apply(price: Int): Int = price + price * percent / 100
}


fun main(){
    val employee = EmployeeNew("Миша", "Wb")
    println(employee.info())

    val manager = Manager("Миша", "WB", 35)
    println(manager.teamInfo())


    val m = MarkupModifier(percent = 15)
    println(m.describe())
    println(m.apply(100))

}