package moduleTwo

/*
Task 1
Создай лямбду double в переменной, которая принимает Int
и возвращает удвоенное число. Вызови для 7.
 */

val number : (Int) -> Int = {x -> x * 2}


/*
Задание 2
Создай лямбду greet которая принимает String (имя)
и возвращает "Привет, {имя}!". Вызови для "Миша"
 */

val greet : (String) -> String = {name -> "Привет, $name!"}


/*
Задание 3
 Создай лямбду isEven которая принимает Int
 и возвращает Boolean (чётное ли). Вызови для 4 и 7
 */

val isEven : (Int) -> Boolean = {x: Int -> x % 2 == 0}

/*
Задание 4
Есть val nums = listOf(5, 12, 8, 130, 44, 3). Через filter оставь только числа больше 10.
 */

val nums = listOf(5, 12, 8, 130, 44, 3)
val higherThanTen = nums.filter{it > 10}

/*
Задание 5
Есть val prices = listOf(100, 200, 300). Через map добавь к каждой цене 20% налог (умножь на 1.2)
 */

val prices = listOf(100, 200, 300)
val applyTax = prices.map{it * 1.2}

/*
Задание 6
 Есть val nums = listOf(1, 3, 5, 7, 8). Через any проверь есть ли хоть одно чётное число.
 */

val numsNew = listOf(1, 3, 5, 7, 8)
val isEvenNew = numsNew.any{it % 2 == 0}

/*
Задание 7
 Есть val names = listOf("anna", "bob", "kate"). Через map сделай каждое имя с заглавной буквы
 (it.replaceFirstChar { c -> c.uppercase() })
 */

val names = listOf("anna", "bob", "kate")
val upperCase = names.map{it.replaceFirstChar { it.uppercase() }}

/*
Задание 8
 Есть val temps = listOf(15, 28, 33, 12, 40).
 Через filter оставь только температуры выше 25, результат выведи
 */

val temps = listOf(15, 28, 33, 12, 40)
val upperTwentyFive = temps.filter{it > 25}


fun main(){
    // println("При умножении мы получили ${number(7)}")
    // println(greet("Миша"))
    // println(isEven(6))
    // println(higherThanTen)
    // println(applyTax)
    // println(isEvenNew)
    // println(upperCase)
    println(upperTwentyFive)
}

