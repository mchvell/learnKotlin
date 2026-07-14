package moduleThree

import kotlin.text.padStart


/*
1. Класс User с валидацией через init
свойства: name (String), age (Int)
в init проверь: если age < 0 — выведи предупреждение (или используй require(age >= 0) { "..." })
создай двух юзеров — валидного и с отрицательным возрастом, посмотри как сработает init
 */

class User(val name: String, val age: Int){
    init {
        require(age >= 0){"Возраст не может быть меньше нуля"}
    }
}

/*
Задача 2 — Order со значением по умолчанию:
свойства: product (String), price (Int), quantity (Int = 1 по умолчанию)
метод total() → price × quantity
создай два заказа: один с quantity, другой без (возьмёт дефолт 1)
 */

class Order(val product: String, val price: Int, val quantity: Int = 1){
    fun total() = price * quantity
}

/*
3 задача (v2)
Класс Duration — длительность чего-либо
primary: seconds (Int) — длительность в секундах
вторичный конструктор: принимает minutes (Int) и extraSeconds (Int),
переводит всё в секунды (minutes * 60 + extraSeconds) и передаёт в primary через this(...)

создай два объекта:
- один напрямую в секундах (primary)
- один через минуты + секунды (вторичный), например 2 минуты 30 секунд

(вторичный оправдан — он преобразует данные, дефолтом так не сделать;
и конфликта нет: primary принимает ОДИН Int, вторичный — ДВА Int)
*/


class Duration(val seconds: Int){
    constructor(minutes: Int, extraSeconds: Int) : this(minutes * 60 + extraSeconds)
}

/*
4. Класс User
Свойства: username (String), age (Int)
Валидация: username не пустой, age от 14 до 120 (границы включительно)
Создай валидный объект и один невалидный — посмотри на сообщение об ошибке
*/

class NewUser(val name: String, val age: Int){
    init {
        require(name.isNotBlank()) {"Имя не может быть пустым"}
        require(age in 14..120) {"Для регистрации пользователю должно быть как минимум 14 и не больше 120"}
    }
}

/*
5. Класс Order
Свойства: productName (String), quantity (Int), pricePerUnit (Int)
quantity по умолчанию = 1
Валидация: quantity > 0, pricePerUnit > 0
Метод total() — возвращает сумму заказа
Создай три объекта: с количеством, без количества (дефолт), и через именованный
аргумент (named argument), пропустив quantity
*/

class NewOrder(val productName: String, val pricePerUnit: Int, val quantity: Int = 1){
    init {
        require(quantity > 0){"Кол-во не могут быть 0 или меньше"}
        require(pricePerUnit > 0){"Цена не может быть 0 или меньше"}
    }
    fun total() = quantity * pricePerUnit
}

/*
6. Класс Percentage
primary: value (Int) — значение в процентах, валидация от 0 до 100
вторичный конструктор: принимает part (Double) и whole (Double),
вычисляет процент как ((part / whole) * 100).toInt() и передаёт в primary
Создай объект напрямую (75) и через дроби (part = 30.0, whole = 40.0)
*/

class Percentage(val value: Int){
    constructor(part: Double, whole: Double): this(((part/whole)* 100).toInt())
    init {
        require(value in 0..100){"Число не может быть меньше 0 или больше 100 $value"}
    }
}

/*
7. Класс TestRun (из твоего мира)
Свойства: totalTests (Int), failedTests (Int)
Валидация: totalTests > 0, failedTests >= 0, failedTests <= totalTests
Метод passRate() — возвращает процент прошедших тестов (Int)
Подумай про порядок операций в целочисленном делении — ты уже наступал
на эти грабли в замыканиях
*/

class TestRun(val totalTests: Int, val failedTests: Int) {
    init {
        require(totalTests > 0){"Итоговое количество тестовых сценариев не может быть равно или меньше нуля, $totalTests"}
        require(failedTests >= 0){"Количество упавших тестов не может быть отрицательным, $failedTests"}
        require(failedTests <= totalTests){"Упавших тестов не может быть больше общего количества"}
    }

    fun passRate() : Int {
        val totalPercentage = 100 - failedTests * 100 / totalTests
        return totalPercentage
    }
}

/*
8. Класс Money
primary: amountInCents (Int) — сумма в копейках, валидация >= 0
вторичный конструктор: принимает rubles (Int) и kopecks (Int),
переводит в копейки и передаёт в primary
Метод format() — возвращает String вида "1250.50 ₽" из копеек
Создай объект через рубли+копейки и выведи format()
*/

class Money(val amountInCents: Int){
    init {
        require(amountInCents >= 0){"баланс не может быть отрицательным, $amountInCents"}
    }
    constructor(rubles: Int, kopecks: Int) : this(rubles * 100 + kopecks)
    fun format(): String {
        val kopecksFormat = (amountInCents % 100).toString().padStart(2, '0')
        return "${amountInCents / 100},${kopecksFormat} ₽"
    }
}

/*
9. Класс Employee
Свойства: name (String), salary (Int), department (String, по умолчанию "QA")
Валидация: name не пустой и не из пробелов, salary > 0
Метод annualSalary() — возвращает годовую зарплату (Int)
Создай три объекта: с департаментом, без него (дефолт),
и через named arguments в перепутанном порядке (salary последним)
*/

class Employee(val name: String, val salary: Int, val department: String = "QA"){
    init {
        require(name.isNotBlank()){"Имя не может быть пустым или из пробелов, $name"}
        require(salary > 0){"Зарплата не может быть 0 или меньше, $salary"}
    }
    fun annualSalary() : Int = salary * 12
}

/*
2. Класс Discount
primary: percent (Int), валидация 0..100
вторичный конструктор: принимает oldPrice (Int) и newPrice (Int),
вычисляет процент скидки и передаёт в primary
Валидация подумай сам: какие oldPrice/newPrice дают осмысленную скидку?
(подсказка: newPrice дороже oldPrice — это скидка?)
Метод apply(price: Int) — возвращает цену после скидки (Int)
Проверь на Discount(oldPrice = 1000, newPrice = 750) → percent должен быть 25
*/

class Discount(val percent: Int){
    init {
        require(percent in 0..100){"Процент не может быть меньше нуля или больше 100, $percent"}
    }
    constructor(oldPrice: Int, newPrice: Int) : this((oldPrice - newPrice) * 100 / oldPrice){
        require(oldPrice > newPrice){"Новая цена должна быть ниже старой"}
    }
    fun apply(price: Int): Int = price - price * percent / 100
}


/*
3. Класс Timer  ← ТЫ ЗДЕСЬ
primary: totalSeconds (Int), валидация >= 0
вторичный конструктор №1: hours (Int), minutes (Int), seconds (Int) — три параметра
вторичный конструктор №2: minutes (Int), seconds (Int) — два параметра
Оба переводят всё в секунды и делегируют в primary
Метод display() — возвращает String вида "01:05:30" (часы:минуты:секунды,
каждая часть дополнена нулём до двух знаков — padStart тебе знаком)
*/


class Timer(val totalSeconds: Int){
    init {
        require(totalSeconds >= 0){"Количество секунд не может быть меньше нуля, $totalSeconds"}
    }
    constructor(hours: Int ,minutes: Int, seconds: Int) : this(hours * 3600 + minutes * 60 + seconds){
        require(hours >= 0){"Чассов не может быть меньше нуля, $hours"}
        require(minutes >= 0){"Минут не может быть меньше нуля, $minutes"}
        require(seconds >= 0){"Секунд не можеть быть меньше нуля, $seconds"}
    }
    constructor(minutes: Int, seconds: Int) : this(minutes * 60 + seconds)
    fun display(): String{
        fun pad(value: Int): String = value.toString().padStart(2, '0')
        val hours = pad(totalSeconds / 3600)
        val minutes = pad((totalSeconds % 3600) / 60)
        val seconds = pad(totalSeconds % 60)
        return "${hours}:${minutes}:${seconds}"
    }
}

/*
BugReport
Свойства: title (String), severity (Int), stepsToReproduce (Int, по умолчанию 1)
Валидация: title не blank, severity в диапазоне 1..4 (1 = critical, 4 = low),
stepsToReproduce > 0
Метод isCritical() — возвращает Boolean (severity == 1)
Создай critical-баг и low-баг, выведи isCritical() для обоих
Внимание: у severity диапазон — сообщение должно описывать ОБЕ границы
*/

class BugReport(val title: String, val severity: Int, val stepsToReproduce: Int = 1){
    init {
        require(title.isNotBlank()){"Название не может быть пустым, $title"}
        require(severity in 1..4){"Severity должно быть в диапозоне от 1 до 4, $severity"}
        require(stepsToReproduce > 0){"Количество шагов для воспроизведение должно быть > 0, $stepsToReproduce"}
    }

    fun isCritical(): Boolean = severity == 1
}

/*
Rating
primary: stars (Int), валидация 1..5
вторичный конструктор: positiveReviews (Int), totalReviews (Int),
переводит долю положительных в звёзды: positiveReviews * 5 / totalReviews,
но не меньше 1 (coerceAtLeast или if — оба ок)
Опасное место: totalReviews = 0
*/


class Rating(val stars: Int){
    init {
        require(stars in 1..5){"Рейтинг должен быть в промежутке от 1 до 5, $stars"}
    }
    constructor(positiveReviews: Int, totalReviews: Int) : this((positiveReviews * 5 / totalReviews).coerceAtLeast(1))
}

fun main(){
    val user1 = User("Alice", 25)
    val userX = User("Bob", -1)
    println(user1)
    println(userX)

    val item = Order("Молоко", 199)
    println(item.total())

    val seconds = Duration(20)
    val minutes = Duration(30, 50)

    println(seconds.seconds)
    println(minutes.seconds)

    val user = NewUser("", 13)
    val user2 = NewUser("Миша", 14)

    println("${user2.name} age is ${user2.age}")
    println("${user.name} is ${user.age}")

    val item1 = NewOrder("Зубная нить", 25, 2)
    val item2 = NewOrder("Носки", 30)
    val item3 = NewOrder("Бритва", 99, quantity = 10)

    val percent = Percentage(25)
    println(percent.value)

    val x = Percentage(part = 30.0, whole = 0.0)
    println(x.value)

    val test = TestRun(3, 1)
    println(test.passRate())

    val roubles = Money(rubles = 10, kopecks = 5)
    println(roubles.format())

    val aqa = Employee("Миша", 3500, "AQA")
    println(aqa.annualSalary())

    val middleQa = Employee("Андрей", 2900)
    println(middleQa.annualSalary())

    val seniorDev = Employee(department = "R&D", name = "Борис", salary = 7000)
    println(seniorDev.annualSalary())

    val discountedItem = Discount(oldPrice = 0, newPrice = 0)
    println(discountedItem.percent)
    println(discountedItem.apply(1000))

    val time = Timer(10, 2, 5)
    println(time.totalSeconds)
    println(time.display())

    val critical = BugReport("Пропала кнопка", 1)
    println(critical.isCritical())

    val low = BugReport("Отличается шрифт", 4)
    println(low.isCritical())
}