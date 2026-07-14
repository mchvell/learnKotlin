package moduleTwo
import moduleThree.Timer

/*
1. makeBudgetGuard
Принимает: лимит бюджета limit (Int)
Возвращает: функцию (Int) -> Boolean которая принимает трату, вычитает из бюджета,
и возвращает true если хватило денег (бюджет не ушёл в минус), false если не хватило
Замыкание помнит остаток бюджета
Пример: makeBudgetGuard(1000), (300)→true (осталось 700), (800)→false (не хватает)
 */

fun makeBudgetGuard(limit: Int): (Int) -> Boolean {
    var budget = limit
    return {expense ->
        if(expense <= budget){
        budget -= expense
        true}
        else {
            false
        }
    }
}

/*
2. makeStock
Принимает: количество товара на складе quantity (Int)
Возвращает: функцию (Int) -> Boolean которая принимает заказ,
и если товара хватает — уменьшает склад и возвращает true, иначе оставляет склад как есть и возвращает false
Пример: makeStock(10), (3)→true (осталось 7), (8)→false (мало, склад всё ещё 7)
 */

fun makeStock(quantity: Int): (Int) -> Boolean {
    var stocks = quantity
    return {stock ->
        if(stocks - stock > 0){
            stocks -= stock
            true
        }
        else {false}
    }
}

/*
3. makeFreeTrials
Принимает: количество бесплатных попыток freeCount (Int)
Возвращает: функцию (Int) -> Int которая принимает цену:
первые freeCount вызовов возвращает 0 (бесплатно), дальше — полную цену
Пример: makeFreeTrials(2), (100)→0, (100)→0, (100)→100, (100)→100
 */

fun makeFreeTrials(freeCount: Int): (Int) -> Int {
    var count = 0
    return {price -> if (count < freeCount) {
        count++
        0
    }
    else {price}}
}

/*
4. makeProgressiveDiscount
Принимает: ничего
Возвращает: функцию (Int) -> Int которая даёт растущую скидку: 1-й вызов 5%, 2-й 10%, 3-й 15%... (скидка = номер вызова × 5%)
Замыкание помнит номер вызова
Пример: (1000)→950 (5%), (1000)→900 (10%), (1000)→850 (15%)
 */

fun makeProgressiveDiscount(): (Int) -> Int {
    var orderNum = 1
    return {
        orderPrice ->
        val discount = orderPrice - orderPrice * orderNum * 5 / 100
        orderNum++
    discount}
}

/*
5. makeStreak
Принимает: ничего
Возвращает: функцию (Boolean) -> Int которая считает серию подряд: если передали true
— увеличивает серию и возвращает её, если false — сбрасывает серию в 0 и возвращает 0
Пример: (true)→1, (true)→2, (true)→3, (false)→0, (true)→1
 */

fun makeStreak(): (Boolean) -> Int {
    var streak = 0
    return {if (it) {
        streak++
        streak
    } else {
        streak = 0
        streak
    }
    }
}

/*
6. makeLoyaltyPoints
Принимает: ничего
Возвращает: (Int) -> Int — принимает сумму покупки, начисляет 1 балл за каждые 100 рублей,
копит баллы, возвращает общий баланс баллов
Пример: (250)→2, (150)→3, (500)→8 (копит: 2+1+5)
 */

fun makeLoyaltyPoints(): (Int) -> Int {
    var points = 0
    return { sum ->
        points += sum / 100   // если sum<100, прибавит 0 — не страшно
        points
    }
}

/*
2. makeWarehouse
Принимает: вместимость capacity (Int)
Возвращает: (Int) -> Boolean — принимает поставку, если влезает
на склад (не превысит capacity с учётом уже лежащего) — добавляет и true, иначе false без изменений
Пример: makeWarehouse(100), (60)→true (склад 60), (50)→false (60+50>100), (40)→true (склад 100)
 */

fun makeWarehouse(capacity: Int): (Int) -> Boolean {
    var occupied = 0
    return {delivery ->
        if(occupied + capacity <= capacity){
            occupied += delivery
            true
        }
        else {
            false
        }
    }
}

/*
3. makeLivesCounter
Принимает: начальное число жизней lives (Int)
Возвращает: () -> Int — каждый вызов отнимает одну жизнь и возвращает остаток, но не уходит ниже 0
Пример: makeLivesCounter(3), →2, →1, →0, →0 (не минус)
 */

fun makeLivesCounter(lives: Int): () -> Int {
    var currentLives = lives
    return {
        if(currentLives > 0){
            currentLives--
        }
        currentLives
    }
}

/*
4. makeWinStreak
Принимает: ничего
Возвращает: (Boolean) -> Int — true увеличивает серию побед,
false сбрасывает в 0 (как makeStreak, повторяем сброс)
Пример: (true)→1, (true)→2, (false)→0, (true)→1
 */

fun makeWinStreak(): (Boolean) -> Int {
    var streak = 0
    return {if(it){
        streak++
        streak
    }
        else {
            streak = 0
        0
        }
    }
}

/*
5. makePromoCode
Принимает: лимит активаций maxActivations (Int) и скидку discount (Int)
Возвращает: (Int) -> Int — применяет скидку к цене пока активаций
меньше лимита (считает использования внутри), после лимита — полная цена
Пример: makePromoCode(2, 10), (1000)→900, (1000)→900, (1000)→1000
 */

fun makePromoCode(maxActivations: Int, discount: Int): (Int) -> Int {
    var limitActivations = maxActivations
    return {price ->
        if (limitActivations > 0){
            val discounted = price - price * discount / 100
            limitActivations --
            discounted
        }
        else {
            price
        }
    }
}

/*
6. makeCashback
Принимает: процент кэшбэка percent (Int)
Возвращает: (Int) -> Int — принимает сумму покупки, начисляет кэшбэк
(percent% от покупки), копит общий кэшбэк, возвращает накопленный баланс
Пример: makeCashback(5), (1000)→50, (2000)→150 (50+100)
 */

fun makeCashback(percent: Int): (Int) -> Int {
    var totalCashback = 0
    return {productPrice ->
        totalCashback += productPrice * percent / 100
        totalCashback}
}

/*
7. makeRateLimiter
Принимает: лимит запросов maxRequests (Int)
Возвращает: () -> Boolean — каждый вызов это запрос;
возвращает true пока запросов меньше лимита, false когда исчерпано
Пример: makeRateLimiter(3), →true, →true, →true, →false, →false
 */

fun makeRateLimiter(maxRequests: Int): () -> Boolean {
    var counter = 0
    return {
        counter++
        counter <= maxRequests
    }
}

/*
8. makeAveragePrice
Принимает: ничего
Возвращает: (Int) -> Int — принимает цену, копит сумму и количество,
возвращает текущее среднее всех переданных цен
Пример: (100)→100, (200)→150 (среднее 100,200), (300)→200 (среднее 100,200,300)
 */

fun makeAveragePrice(): (Int) -> Int {
    var sum = 0
    var counter = 0
    return {price ->
        sum += price
        counter++
        val avgPrice = sum / counter
        avgPrice
    }
}

/*
9. makeToggleDiscount
Принимает: скидку discount (Int)
Возвращает: (Int) -> Int — через раз применяет скидку:
1-й вызов со скидкой, 2-й без, 3-й со скидкой... (переключатель + скидка)
Пример: makeToggleDiscount(20), (1000)→800, (1000)→1000, (1000)→800
(подсказка: булев флаг как в makeToggle + скидка)
 */

fun makeToggleDiscount(discount: Int): (Int) -> Int {
    var state = true
    return {price ->
        if (state){
            val discountedPrice = price - price * discount / 100
            state = false
            discountedPrice
        }
        else {
            state = true
            price
        }
    }
}

/*
10. makeBonusStreak
Принимает: ничего
Возвращает: (Boolean) -> Int — считает серию успехов (true),
но возвращает бонус = серия × 10. false сбрасывает серию в 0
Пример: (true)→10, (true)→20, (true)→30, (false)→0, (true)→10
 */

fun makeBonusStreak(): (Boolean) -> Int {
    var streak = 0
    return {it ->
        if (it){
            streak++
            streak * 10
        }
        else {
            streak = 0
            streak
        }
    }
}

fun main(){
    val makeBudgetGuard = makeBudgetGuard(100)
    println(makeBudgetGuard(10))
    println(makeBudgetGuard(91))

    val stocksCheck = makeStock(5)
    println(stocksCheck(1))
    println(stocksCheck(4))

    val tryFreeSubscription = makeFreeTrials(3)
    println(tryFreeSubscription(200))
    println(tryFreeSubscription(400))
    println(tryFreeSubscription(400))
    println(tryFreeSubscription(500))

    val makeDiscount = makeProgressiveDiscount()
    println(makeDiscount(10000))
    println(makeDiscount(10000))

    val streakCount = makeStreak()
    println(streakCount(true))
    println(streakCount(true))
    println(streakCount(false))

    val points = makeLoyaltyPoints()
    println(points(250))
    println(points(25))
    println(points(555))

    val capacity = makeWarehouse(100)
    println(capacity(60))
    println(capacity(60))

    val counter = makeLivesCounter(3)
    println(counter()) // 2
    println(counter()) // 1
    println(counter()) // 0
    println(counter()) // 0

    val x = makeWinStreak()
    println(x(true))
    println(x(true))
    println(x(false))

    val isDiscountAvaliable = makePromoCode(2, 20)
    println(isDiscountAvaliable(1000))
    println(isDiscountAvaliable(1000))
    println(isDiscountAvaliable(1000))

    val getCashback = makeCashback(10)
    println(getCashback(1000))
    println(getCashback(2000))

    val checkLimits = makeRateLimiter(3)
    println(checkLimits())
    println(checkLimits())
    println(checkLimits())
    println(checkLimits())

    val checkAvgPrice =makeAveragePrice()
    println(checkAvgPrice(100))
    println(checkAvgPrice(200))

    val checkDiscount = makeToggleDiscount(10)
    println(checkDiscount(100))
    println(checkDiscount(100))
    println(checkDiscount(100))
    println(checkDiscount(100))

    val checkWinStreak = makeBonusStreak()
    println(checkWinStreak(true))
    println(checkWinStreak(true))
    println(checkWinStreak(false))
    println(checkWinStreak(true))

}