package moduleTwo
/*
Задание 1
Напиши функцию operate которая принимает два числа и функцию (Int, Int) -> Int,
применяет функцию к числам и возвращает результат. Вызови с операцией сложения и умножения.
 */

fun operate(x: Int, y: Int, action: (Int, Int) -> Int) : Int {
    return action(x,y)
}

/*
Задание 2
Напиши функцию repeatAction которая принимает число n и функцию () -> Unit,
и выполняет эту функцию n раз
 */

fun repeatAction(n: Int, op: () -> Unit){
    for (i in 1..n){
        op()
    }
}

/*
Задание 3
Напиши функцию transformList которая принимает List<Int> и функцию (Int) -> Int,
применяет функцию к каждому элементу и возвращает новый список. (По сути свой map.
 */

fun transformList(n: List<Int>, mapper: (Int) -> Int): List<Int> {
    val result = mutableListOf<Int>()
    for (i in n) {
        val mapped = mapper(i)
        result.add(mapped)
    }
    return result
}

/*
Задание 4
Напиши функцию filterList которая принимает List<Int> и функцию (Int) -> Boolean,
возвращает только те элементы для которых функция вернула true. (Свой filter.)
 */

fun filterList(n: List<Int>, filter: (Int) -> Boolean): List<Int>  {
    val result = mutableListOf<Int>()
    for (i in n) {
        if (filter(i)) {
            result.add(i)
        }
    }
    return result
}

/*
Задание 5
Напиши функцию processString которая принимает строку и функцию (String) -> String,
применяет её к строке и возвращает результат. Вызови с операцией "сделать заглавными" и
"развернуть наоборот" (it.reversed())
 */

fun processString(s: String, convert: (String)-> String) : String  {
    return convert(s)
}

/*
Задание 6
Принимает: два числа (Int, Int) и операцию (Int, Int) -> Int
Делает: применяет операцию к двум числам
Возвращает: Int (результат операции)
Вызови со сложением
 */

fun calculate(x: Int, z: Int, op: (Int, Int) -> Int) : Int {
    return op(x,z)
}

/*
Задание 7
Принимает: одно число (Int) и операцию (Int) -> Int
Делает: применяет операцию к числу
Возвращает: Int
Вызови с операцией "удвоить"
 */

fun applyTo(x: Int, operate: (Int) -> Int) : Int = operate(x)

/*
Задание 8
Принимает: число (Int) и условие (Int) -> Boolean
Делает: проверяет число условием
Возвращает: Boolean
Вызови с проверкой "больше 10"
 */

fun checkInt(x: Int, op: (Int) -> Boolean) : Boolean = op(x)

/*
Задание 9
doTwice
Принимает: действие () -> Unit
Делает: выполняет это действие два раза
Возвращает: ничего (Unit)
Вызови с печатью текста
 */

fun doTwice(ln: () -> Unit){
    for (i in 1..2) ln()
}

/*
Задание 10
transform
Принимает: строку (String) и операцию (String) -> String
Делает: применяет операцию к строке
Возвращает: String
Вызови с операцией "в верхний регистр" (it.uppercase())
 */

fun transformToUpperCase(s: String, operate: (String) -> String): String = operate(s)

fun main(){
    val add = operate(3,4) { x, y -> x + y }
    println(add)

    val multiply = operate(3,4) { x, y -> x * y }
    println(multiply)

    repeatAction(7, {println("Hello")})
    val result = transformList(listOf(1,2,3,4,5)) {it + 1}
    println(result)

    val r = filterList(listOf(1,2,3,4,5)) {it > 1}
    println(r)

    var transformString = processString("Stan") {it.reversed()}
    println(transformString)

    transformString = processString("stan") {it.replaceFirstChar { c -> c.uppercase() }}
    println(transformString)

    val opSum = operate(3,4) { x, y -> x + y }
    println(opSum)

    val addTwo = applyTo(2){it + 2}
    println(addTwo)

    val hitherThanTen = checkInt(9) {it > 10}
    println(hitherThanTen)

    doTwice { println("doTwice") }

    val transformation = transformToUpperCase("string") {it.uppercase()}
    println(transformation)
}