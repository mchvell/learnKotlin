package moduleTwo

/*
1. makeBankAccount
Принимает: начальный баланс initial (Int)
Возвращает: функцию (Int) -> Int которая принимает сумму депозита, прибавляет к балансу и возвращает новый баланс
Замыкание помнит и меняет баланс между вызовами
Пример: val acc = makeBankAccount(100), acc(50) → 150, acc(30) → 180 (помнит 150!)
 */

fun makeBankAccount(initial: Int) : (Int) -> Int{
    var balance = initial
    return { deposit ->
        balance += deposit
        balance
    }
}

/*
2. makeAccumulator
Принимает: ничего
Возвращает: функцию (Int) -> Int которая суммирует все переданные числа нарастающим итогом
Пример: val acc = makeAccumulator(), acc(10) → 10, acc(5) → 15, acc(3) → 18
 */

fun makeAccumulator(): (Int) -> Int{
    var sum = 0
    return { item ->
        sum += item
        sum
    }
}

/*
3. makeMultiplierWithMemory
Принимает: множитель factor (Int)
Возвращает: функцию (Int) -> Int которая умножает на factor И считает сколько раз её вызвали (выводит счётчик в консоль)
Пример: каждый вызов умножает на factor и печатает "вызов №N"
 */

fun makeMultiplierWithMemory(factor: Int): (Int) -> Int{
    var counter = 0
    return {number ->
        counter++
        print("$counter = ")
        number * factor
    }
}

/*
4. makeLimitedDiscount
Принимает: процент скидки percent (Int) и лимит использований maxUses (Int)
Возвращает: функцию (Int) -> Int которая применяет скидку,
но только первые maxUses раз, дальше возвращает цену без скидки
Замыкание помнит сколько раз уже использовали
Пример: makeLimitedDiscount(20, 2) — первые 2 вызова со скидкой, третий и далее без
 */

fun makeLimitedDiscount(percent: Int, maxUse: Int): (Int) -> Int{
    var counter = 0
    return {price ->
        if (counter < maxUse){
            counter++
            price - ((price * percent) / 100)
        } else {
            price
        }
    }
}

/*
5. makeToggle
Принимает: ничего
Возвращает: функцию () -> Boolean которая переключается между true/false при каждом вызове
Пример: val toggle = makeToggle(), toggle() → true, toggle() → false, toggle() → true
 */

fun makeToggle(): () -> Boolean{
    var state = false
    return {state = !state
        state
    }
}

/*
6.  makeWallet
Принимает: начальную сумму initial (Int)
Возвращает:  (Int) -> Int которая тратит деньги (вычитает переданную сумму из баланса) и возвращает остаток
Замыкание помнит баланс между тратами
Пример: makeWallet(1000), вызов (300) → 700, вызов (200) → 500
 */

fun makeWallet(initial: Int): (Int) -> Int{
    var balance = initial
    return {
        expense -> balance -= expense
        balance
    }
}

/*
7. makeScoreKeeper
Принимает: ничего
Возвращает: функцию (Int) -> Int которая прибавляет очки к счёту и возвращает текущий счёт
Пример: вызов (10) → 10, (5) → 15, (20) → 35
 */

fun makeScoreKeeper(): (Int) -> Int{
    var scores = 0
    return {score -> scores += score
    scores}
}

/*
8. makeStepCounter
Принимает: размер шага step (Int)
Возвращает: функцию () -> Int (без параметров) которая каждый вызов увеличивает счётчик на step
Пример: makeStepCounter(5), вызов → 5, → 10, → 15
 */

fun makeStepCounter(step: Int): () -> Int{
    var counter = 0
    return {counter += step
    counter}
}

/*
9. makePriceWithRising
Принимает: начальную цену start (Int) и процент роста risePercent (Int)
Возвращает: функцию () -> Int которая каждый вызов поднимает цену на процент и возвращает новую
Пример: makePriceWithRising(100, 10), вызов → 110, → 121, → 133 (цена растёт каждый раз)
 */

fun makePriceWithRising(start: Int, risePercent: Int): () -> Int{
    var newPrice = start
    return {newPrice += newPrice * risePercent / 100
    newPrice}
}

/*
10. makeAttempts
Принимает: максимум попыток maxAttempts (Int)
Возвращает: функцию () -> Boolean которая возвращает true пока попытки есть (счётчик меньше лимита), false когда исчерпаны
Пример: makeAttempts(2), вызов → true, → true, → false, → false
 */

fun makeAttempts(maxAttempts: Int): () -> Boolean {
    var used = 0
    return {
        used++
        used <= maxAttempts
    }
}

fun main(){
    val bank = makeBankAccount(100)
    println(bank(50))
    println(bank(50))

    val acc = makeAccumulator()
    println(acc(10))
    println(acc(15))

    val n = makeMultiplierWithMemory(2)
    println(n(10))
    println(n(200))

    val applyDiscountCheck = makeLimitedDiscount(20, 2)
    println(applyDiscountCheck(1000))
    println(applyDiscountCheck(1000))
    println(applyDiscountCheck(1000))

    val stateCheck = makeToggle()
    println(stateCheck())
    println(stateCheck())

    val z = makeWallet(500)
    println(z(10))
    println(z(200))

    val addScore = makeScoreKeeper()
    println(addScore(10))
    println(addScore(20))

    val countSteps = makeStepCounter(3)
    println(countSteps())
    println(countSteps())

    val newPrice = makePriceWithRising(500, 10)
    println(newPrice())
    println(newPrice())

    val tryAttempts = makeAttempts(3)
    println(tryAttempts())
    println(tryAttempts())
    println(tryAttempts())
    println(tryAttempts())

}
