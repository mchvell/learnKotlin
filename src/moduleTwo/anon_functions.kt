package moduleTwo

/*
1. Переменная double — анонимная функция которая принимает Int и возвращает удвоенное. Вызови для 7.
 */

val double = fun(x: Int): Int = x * 2

/*
2. Переменная isPositive — анонимная функция (Int) -> Boolean,
проверяет положительное ли число. Вызови для -3 и 5.
 */

val isPositiveNew = fun(x: Int) = x > 0

/*
3. Переменная fullName — анонимная функция (String, String) -> String, склеивает имя и фамилию через пробел.
 */

val fullName = fun(name: String, surname: String): String = "$name $surname"

/*
4. Напиши функцию-фабрику makeAdder(n: Int) которая возвращает анонимную функцию
(не лямбду) прибавляющую n. Сравни с тем как делал через лямбду.

 */

fun makeAdderNew(n: Int): (Int) -> Int {
    return fun(j: Int) = n + j
}
fun main(){
    println(double(2))
    println(isPositiveNew(-2))
    println(fullName("Миша", "Романов"))
    println(makeAdderNew(2)(4))

}