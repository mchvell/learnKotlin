package moduleThree

import kotlin.math.pow
import kotlin.time.Duration.Companion.minutes

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

fun main(){
    val user1 = User("Alice", 25)
    val user2 = User("Bob", -1)
    println(user1)
    println(user2)

    val item = Order("Молоко", 199)
    println(item.total())

    val seconds = Duration(20)
    val minutes = Duration(30, 50)

    println(seconds.seconds)
    println(minutes.seconds)
}