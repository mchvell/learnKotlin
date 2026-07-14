package moduleTwo

/*
Задание 1
Объяви функцию multiply(a: Int, b: Int): Int.
Затем создай переменную operation типа (Int, Int) -> Int
и присвой ей multiply через ::. Вызови через переменную для (4, 5).
 */

fun multiply(a: Int, b: Int): Int = a * b

val operationsOne : (Int, Int) -> Int = ::multiply
val result = operationsOne(3, 5)

/*
Задание 2
 Объяви функцию sayHello() которая печатает "Привет!".
 Создай переменную greeting типа () -> Unit, присвой ей sayHello через ::. Вызови через переменную
 */

fun sayHello(){
    println("Hello")
}

val greeting : () -> Unit = ::sayHello

/*
Задание 3
Объяви функцию isPositive(n: Int): Boolean.
Создай переменную типа (Int) -> Boolean, присвой через ::, вызови для -5 и 10.
 */

fun isPositive(n: Int) : Boolean = n > 0
val numberOne : (Int) -> Boolean = ::isPositive

/*
Задание 4
Объяви две функции add(a, b) и subtract(a, b) (обе (Int, Int) -> Int).
Создай одну переменную op типа (Int, Int) -> Int (через var),
присвой сначала ::add, вызови, потом переприсвой ::subtract, вызови снова.
Покажи что одна переменная может указывать на разные функции.
 */

fun add(a: Int, b: Int): Int = a + b
fun subtract(a: Int, b: Int): Int = a - b
var op : (Int, Int) -> Int = :: add

/*
Задание 5
Создай переменную square и присвой ей лямбду { x: Int -> x * x }
(без ::, потому что лямбда это уже функция-значение).
Вызови для 7. Подумай — в чём разница между присваиванием через :: (задачи 1-4) и присваиванием лямбды напрямую?
 */

val square = { x: Int -> x * x }


/*
Задание 6
Есть функция fun triple(n: Int) = n * 3.
Сделай так, чтобы переменная f могла вызвать эту функцию, и выведи результат для 6.
 */

fun triple(n: Int) = n * 3
val f : (Int) -> Int = ::triple

/*
Задание 7
Заведи переменную, которая хранит функцию, печатающую "Загрузка...". Вызови её дважды.
 */

fun printLoading(){
    println("Загрузка...")
}
val loading : () -> Unit = ::printLoading

/*
Задание 8
Есть fun isEven(n: Int) = n % 2 == 0.
Передай эту функцию в filter, чтобы из listOf(1, 2, 3, 4, 5, 6)
остались только чётные. (подсказка: filter умеет принимать и ::имя, не только лямбду)
 */

fun isEven(n: Int) = n % 2 == 0
val evenCounter = listOf(1, 2, 3, 4, 5, 6).filter { isEven(it) }

/*
Задание 9
Заведи переменную op которая сначала складывает два числа, потом (переприсвоением) умножает их.
Покажи оба результата для (4, 5).
 */

var operations = {x: Int, y: Int -> x + y}

/*
Задание 10
 У тебя есть переменная с функцией (String) -> Int, которая возвращает длину строки.
 Реализуй её двумя способами в разных переменных: через ссылку на именованную функцию и через лямбду. Вызови обе для "kotlin".
 */

// Вариант 1
val stringLength : (String) -> Int = { x -> x.length }

// Вариант 2
fun lengthOfString(s: String) = s.length
val lS : (String) -> Int = ::lengthOfString


fun main(){
    println(result)
    greeting()
    println(isPositive(-1))
    println(op(1, 3))
    op = ::subtract
    println(op(1, 3))
    println(square(2))
    println(f(3))
    loading()
    loading()
    println(evenCounter)
    println(operationsOne(1, 3))
    operations = {x: Int, y: Int -> x * y}
    println(operationsOne(2, 3))
    println(stringLength("Hello"))
    println(lS("Test"))
}


