package moduleOne


val temps = listOf(18, 23, 15, 30, 27, 12, 25)

fun daysCounter(){
    var days = 0
    for(i in temps){
        if(i > 20){
            days++
        }
    }
    println(days)
}


val nums = listOf(7, 7, 7, 7)

fun maxInt(){
    var maxInt = nums[0]
    for(num in nums){
        if(num > maxInt){
            maxInt = num
        }
    }
    println(maxInt)
}


val prices = listOf(150)

fun avgPrice(){
    val pricesLength = prices.size
    var avgPrice = 0.0
    var itemPrice = 0
    for(price in prices){
        itemPrice += price
    }
    avgPrice = itemPrice.toDouble() / pricesLength

    println(avgPrice)
}


fun counter(){
    for(i in 1..20){
        println(i)
        if (i % 5 == 0){
            println("--- пятёрка ---")
        }
        }
}

val sentence = "kotlin is fun"

fun whiteSpaceFinder(){
    var whiteSpaces = 0
    for(i in sentence){
        if(i == ' '){
            whiteSpaces++
        }
    }
    print(whiteSpaces)
}

val salaries = listOf(3000, 4500, 2800, 5200, 3900)

fun salaryRaiser(){
    for (salary in salaries) {
        val raised = (salary * 1.1).toInt()
        val finalSalary = if (raised > 5000) 5000 else raised
        println("ЗП: $finalSalary")
    }
}

var n = 1
var steps = 0

fun cycleCounter(){
    while(n * 3 <= 1000){
        n *= 3
        steps++
    }
    println("Значение: $n, итераций: $steps")
}


val num = 9

fun isPrime(){
    var i = 2
    var isPrime = true
    while(i < num){
        if (num % i == 0) {
            isPrime = false
        }
        i++
    }
    println("$num простое число $isPrime")

}


var lives = 3

fun gameCycle(){
    var totalDrops = 0
    while(lives > 0){
        val drop = (1..6).random()
        if(drop == 1){
            lives--
        }
        totalDrops++
    }
    println("Жизни закончились за $totalDrops хода")
}

var numm = 64

fun itter(){
    var iterations = 0
    while(numm > 1){
        numm /=2
        iterations++
    }
    println("Колличество иттерация = $iterations")
}


var balance = 1000.00

fun piggy(){
    var year = 0
    while(balance < 2000){
        balance *= 1.05
        year++
    }
    println("За $year лет удалось накопить $balance")
}


var subscribers = 500

fun subsCalculator(){
    val newDailySubscribers = 50
    var days = 0
    while(subscribers < 1000){
        subscribers += newDailySubscribers
        days++
    }
    println("За $days отметка подписчиков достигла $subscribers")
}


var fuel = 60

fun fuelCalculator(){
    val fuelConsumption = 7
    var rides = 0
    while(fuel - fuelConsumption > 0){
        fuel -= fuelConsumption
        rides++
    }
    println("60 литров бензина хватит на $rides, осталось $fuel")
}


var health = 100

fun gameRpg(){
    var round = 0
    while(health>0){
        val monsterAttack = (5..20).random()
        health -= monsterAttack
        round++
    }
    println("Герой продержался $round пока его здоровье не опустилось до $health")
}


val price = 50000

fun notebookInvestment(){
    var months = 0
    var monthlyTopUp = 0
    while(monthlyTopUp < price){
        monthlyTopUp += 3000
        months++
    }
    println("На ноутбук я накоплю $monthlyTopUp за $months")
}


var views = 100.00

fun viewsCalculator(){
    var days = 0

    while(views <= 1000){

        views *= 1.2
        days++
    }
    println("За $days видео набрало ${views.toInt()}")
}