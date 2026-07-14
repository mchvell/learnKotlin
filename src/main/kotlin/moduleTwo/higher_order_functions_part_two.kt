package moduleTwo

/*
1 makeAdder
Принимает: число n (Int)
Возвращает: функцию (Int) -> Int которая прибавляет n к своему аргументу
Проверь: makeAdder(5) создаёт функцию, вызов с 3 даёт 8
 */

fun makeAdder(n: Int) : (Int) -> Int{
    return { i -> i + n }
}

/*
2. makeMultiplier
Принимает: множитель factor (Int)
Возвращает: функцию (Int) -> Int которая умножает аргумент на factor
Проверь: makeMultiplier(3), вызов с 4 даёт 12
 */

fun makeMultiplier(factor: Int) : (Int) -> Int{
    return { i -> i * factor }
}

/*
3. makeGreeter
Принимает: приветствие greeting (String), например "Привет"
Возвращает: функцию (String) -> String которая принимает имя и составляет "{greeting}, {имя}!"
Проверь: makeGreeter("Привет"), вызов с "Миша" даёт "Привет, Миша!"
 */

fun makeGreeter(greeting: String) : (String) -> String = {i -> greeting + i}

/*
4. makeDiscount (доменная, твоя любимая)
Принимает: процент скидки percent (Int)
Возвращает: функцию (Int) -> Int которая применяет скидку к цене
Проверь: makeDiscount(20), вызов с 1000 даёт 800
 */

fun makeDiscount(percent: Int) : (Int) -> Int = {i -> i - (i * percent) / 100}

/*
5. makeThreshold
Принимает: порог limit (Int)
Возвращает: функцию (Int) -> Boolean которая проверяет "больше ли порога"
Проверь: makeThreshold(100), вызов с 150 даёт true, с 50 — false
 */

fun makeThreshold(limit: Int) : (Int) -> Boolean{
    return { i -> i > limit }
}

/*
6. makeTaxCalculator
Принимает: ставку налога taxPercent (Int)
Возвращает: функцию (Int) -> Int которая добавляет налог к цене
Пример: makeTaxCalculator(20), вызов с 1000 → 1200
 */

fun makeTaxCalculator(taxPercent: Int) : (Int) -> Int = {i -> i + ((i * taxPercent)/100) }

/*
7. makeTieredDiscount
Принимает: процент скидки percent (Int) и минимальную сумму minAmount (Int)
Возвращает: функцию (Int) -> Int которая применяет скидку только если цена ≥ minAmount, иначе оставляет цену как есть
Пример: makeTieredDiscount(15, 1000) — для 2000 даст 1700, для 500 даст 500 (скидки нет, мало)
(внутри возвращаемой функции — условие!)
 */

fun makeTieredDiscount(percent: Int, minAmount: Int) : (Int) -> Int {
    return { price ->
        if (price >= minAmount) {
            price - price * percent / 100
        } else {
            price
        }
    }
}

/*
8. makeValidator
Принимает: минимальную min и максимальную max длину (Int, Int)
Возвращает: функцию (String) -> Boolean которая проверяет что длина строки в диапазоне [min, max]
Пример: makeValidator(3, 10) — для "kotlin" → true, для "ab" → false
 */

fun makeValidator(min: Int, max: Int) : (String) -> Boolean{
    return {i -> i.length in min..max}
}

/*
9. makeShippingCalculator
Принимает: порог бесплатной доставки freeFrom (Int) и стоимость доставки cost (Int)
Возвращает: функцию (Int) -> Int которая по сумме заказа возвращает итог с доставкой: если заказ ≥ freeFrom — доставка бесплатна (вернуть сумму), иначе прибавить cost
Пример: makeShippingCalculator(5000, 300) — для 6000 → 6000, для 3000 → 3300
 */

fun makeShippingCalculator(freeFrom: Int, cost: Int) : (Int) -> Int{
    return {orderPrice -> if(orderPrice > freeFrom){orderPrice} else{orderPrice + cost} }
}

/*
10. makeCounter (вот это интересная — с состоянием)
Принимает: ничего
Возвращает: функцию () -> Int которая при каждом вызове возвращает число на 1 больше предыдущего (1, 2, 3...)
Пример: val counter = makeCounter(), потом counter() → 1, counter() → 2, counter() → 3
(подсказка: заведи var count = 0 в фабрике ДО возвращаемой лямбды,
и лямбда пусть его увеличивает. Это чистое замыкание — функция помнит и МЕНЯЕТ переменную фабрики)
*/

fun makeCounter(): () -> Int{
    var count = 0
    return {
        count++
        count
    }
}

fun main(){
    val five = makeAdder(5)
    println(five(10))

    val n = makeMultiplier(3)
    println(n(4))

    val greet = makeGreeter("Привет ")
    println(greet("Миша!"))

    val discount = makeDiscount(20)
    println(discount(1000))

    val limitChecker = makeThreshold(100)
    println(limitChecker(50))

    val addTax = makeTaxCalculator(20)
    println(addTax(1000))

    val minOrderDiscount = makeTieredDiscount(5, 500)
    println(minOrderDiscount(1000))

    val wordLengthChecker = makeValidator(4,5)
    println(wordLengthChecker("Tea"))

    val freeShippingChecker = makeShippingCalculator(5000, 300)
    println(freeShippingChecker(4001))

    val counter = makeCounter()
    println(counter())
    println(counter())
}