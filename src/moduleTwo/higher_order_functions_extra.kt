package moduleTwo

/*
1. applyToAll
Принимает: список List<Int> и действие (Int) -> Unit
Делает: выполняет действие для каждого элемента (например печатает)
Возвращает: ничего (Unit)
Вызови с печатью каждого элемента
 */

fun applyToAll(x: List<Int>, op: (Int) -> Unit){
    for (i in x) op(i)
}

/*
2. sumWith
Принимает: два числа (Int, Int) и операцию (Int, Int) -> Int
Делает: применяет операцию, потом прибавляет к результату 10
Возвращает: Int
Вызови с умножением — для (3, 4) должно выйти 22 (3×4=12, +10=22)
 */

fun sumWith(i: Int, f: Int, op: (Int, Int) -> Int): Int = op(i, f)

/*
3. allMatch
Принимает: список List<Int> и условие (Int) -> Boolean
Делает: проверяет удовлетворяют ли все элементы условию (свой all)
Возвращает: Boolean
Вызови с проверкой "положительное"
(подсказка: если хоть один не подходит — сразу false через return; дошёл до конца — true)
 */

fun allMatch(nums: List<Int>, op: (Int) -> Boolean): Boolean{
    for(i in nums) if(!op(i)) return false
    return true
}

/*
4. transformOrKeep
Принимает: число (Int), условие (Int) -> Boolean и операцию (Int) -> Int
Делает: если число проходит условие — применяет операцию, иначе возвращает число без изменений
Возвращает: Int
Вызови: если число чётное — удвоить, иначе оставить. Для 4 → 8, для 5 → 5
(тут два параметра-функции сразу!)
 */

fun transformOrKeep(num: Int, condition: (Int) -> Boolean, operation : (Int) -> Int): Int {
    if(condition(num)) return operation(num)
    return num
}

/*
5. repeatAndCollect
Принимает: число n (Int) и функцию (Int) -> Int
Делает: применяет функцию к числам от 1 до n, собирает результаты в список
Возвращает: List<Int>
Вызови с операцией "квадрат" — для n=4 даст [1, 4, 9, 16]
 */

fun repeatAndCollect(n: Int, op: (Int) -> Int): List<Int> {
    val result = mutableListOf<Int>()
    for(i in 1..n){
        val mapped = op(i)
        result.add(mapped)
    }
    return result
}


/*
8. filterProducts
Принимает: список цен List<Int> и критерий (Int) -> Boolean
Делает: оставляет товары подходящие под критерий
Возвращает: List<Int>
Вызови с критерием "дешевле 1000"
 */

/*
6. applyPricing
Принимает: базовую цену (Int) и стратегию ценообразования (Int) -> Int
Делает: применяет стратегию к цене
Возвращает: Int (итоговая цена)
Вызови дважды: со скидкой 20% и с наценкой 15%
 */

fun applyPricing(price: Int, operate: (Int) -> Int): Int = operate(price)

/*
7. calculateTotal
Принимает: список цен List<Int> и функцию-модификатор (Int) -> Int (применяется к каждой цене)
Делает: применяет модификатор к каждой цене и суммирует
Возвращает: Int (общая сумма)
Вызови с модификатором "+5% налог на каждый товар"
 */

fun calculateTotal(prices: List<Int>, operate: (Int) -> Int): Int {
    var total = 0
    for(i in prices){
        total += operate(i)
    }
    return total
}
fun filterProducts(products: List<Int>, condition: (Int) -> Boolean): List<Int>{
    val result = mutableListOf<Int>()
    for (product in products){
        if(condition(product)){
            result.add(product)
        }
    }
    return result
}

/*
9. processOrder
Принимает: сумму заказа (Int), условие бесплатной доставки (Int) -> Boolean и расчёт доставки (Int) -> Int
Делает: если условие выполнено — доставка бесплатна (возвращает сумму как есть), иначе — добавляет стоимость доставки
Возвращает: Int (итог с доставкой или без)
Вызови: бесплатная доставка если заказ больше 5000, иначе +300
 */

fun processOrder(sum: Int, condition: (Int) -> Boolean, op: (Int) -> Int): Int{
    if(!condition(sum)){
        return op(sum)
    }
    return sum
}

/*
10. applyDiscountIf
Принимает: цену (Int), условие скидки (Int) -> Boolean и размер скидки (Int) -> Int
Делает: если цена проходит условие — применяет скидку, иначе оставляет
Возвращает: Int
Вызови: скидка 10% если цена выше 2000, иначе без скидки
 */


fun applyDiscountIf(price: Int, condition: (Int) -> Boolean, operate: (Int) -> Int): Int{
    if(condition(price)){
        return operate(price)
    }
    return price
}

fun main(){
    applyToAll(listOf(1, 2, 3, 4, 5), {println(it)})

    val multiply = sumWith(3,4) {x, y -> (x * y) + 10}
    println(multiply)

    val positiveMatcher = allMatch(listOf(-2, 3, 4, 5, 6, 7, 8, 9, 10)){it > 0}
    println(positiveMatcher)

    val transformEvenNum = transformOrKeep(4, { it % 2 == 0 }, {it * 2})
    println(transformEvenNum)

    val m = repeatAndCollect(4){it * it}
    println(m)

    val discountedPrice = applyPricing(300){(it * (100 - 20)) / 100}
    println(discountedPrice)

    val markUpPrice = applyPricing(300){(it * (15 + 100)) / 100}
    println(markUpPrice)

    val applyTax = calculateTotal(listOf(100, 200, 300)){(it * (5 + 100))/100}
    println(applyTax)

    val newProducts = filterProducts(listOf(1000, 2000, 999, 4, 5)) { it < 1000 }
    println(newProducts)

    val isDeliveryFree = processOrder(3000,{it > 5000}, {it + 300})
    println(isDeliveryFree)

    val isDiscountAvailable = applyDiscountIf(2001, {it > 2000},
        {(it * (100 - 10))/100})
    println(isDiscountAvailable)
}