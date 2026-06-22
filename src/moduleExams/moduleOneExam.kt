package module_exams

// Задание 1
val name = "Джек"
val age = 28
val salary = 3500.50
val isRemote = true

fun main(){
    println("$name. Ему $age лет. Он зарабатывает $salary и работает удаленно: $isRemote")
}

// Задание 2
val price = "1499"

fun main(){
    val finalPrice = price.toDouble() * 1.2
    println("%.2f".format(finalPrice))
}


// Задание 3
var seconds = 7384

fun main(){
    val hours = seconds / 3600
    val minutes = (seconds - (3600 * hours)) / 60
    seconds = (seconds - (3600 * hours)) % 60
    println("$hours ч, $minutes мин, $seconds сек")
}

val score = 78


// Задание 4
fun main(){
    when(score){
        in 90..100 -> println("A")
        in 80..89 -> println("B")
        in 70..79 -> println("C")
        in 60..69 -> println("D")
        else -> println("E")
    }
}


// Задание 5
val hour = 14

fun main(){
    when(hour){
        in 6..11 -> println("Утро")
        in 12..17 -> println("День")
        in 18..22 -> println("Вечер")
        else -> println("Ночь")

    }
}

// Задание 6
val age = 19
val isStudent = false

fun main(){
    val isDiscountAvailable = if((age <= 18) or (isStudent == true)) "Скидка 20%" else "Скидка не доступна"
    println(isDiscountAvailable)
}

// Задание 7
fun main(){
    for (i in 1..50){
        if(i % 4 == 0 && i % 6 == 0){
            println("FourSix")
        }
        else if(i % 4 == 0){
            println("Four")
        }
        else if(i % 6 == 0){
            println("Six")
        }
        println(i)
    }
}


// Задание 8
var balance = 10000

fun main(){
    var counter = 0
    while(balance >0){
        val consumption = (800..1500).random()
        balance -= consumption
        counter++
    }
    println("Я смог продержаться с балансом $balance $counter месяцев")
}

// Задание 9
fun main(){
    var counter = 0
    while(true) {

        val cubeOne = (1..6).random()
        val cubeTwo = (1..6).random()
        counter++
        if(cubeOne == cubeTwo){
            println("$cubeOne == $cubeTwo на $counter попытку")
            break
        }
    }
}


// Задание 10
fun main(){
    var result = 0
    for (i in 1..100) {
        if (i % 3 == 0){
            result += i
            if (result > 600) break
        }
    }
    println(result)
}


// Задание 11
val nums = arrayOf(34, 12, 87, 45, 23, 99, 8)

fun main(){
    var minNum = nums[0]
    var maxNum = nums[0]
    var minIndex = 0
    var maxIndex = 0
    for(i in 0 until nums.size){
        if(nums[i] < minNum){
            minNum = nums[i]
            minIndex = i
        }
        else if(nums[i] > maxNum){
            maxNum = nums[i]
            maxIndex = i
        }
    }
    println("минимальное число в массиве $minNum с индексом $minIndex")
    println("Максимальное числов в массиве $maxNum c индексом $maxIndex")
}


// Задание 12
val salaries = arrayOf(45000, 80000, 32000, 95000, 60000)

fun main(){
    var avgSalary = 0.0
    var resultBelowAvgSalary = 0
    for (i in salaries){
        avgSalary +=i
    }
    avgSalary = avgSalary / salaries.size
    for (i in salaries){
        if(avgSalary > i){
            resultBelowAvgSalary++
        }
    }
    println("Средняя зарплата $avgSalary, зарплат выше средней $resultBelowAvgSalary")
}


// Задание 13
val nums = arrayOf(5, 8, 3, 12, 7, 9, 2)

fun main(){
    println(nums.contentToString())
    for(i in 0 until nums.size){
        if(nums[i] < 6){
            nums[i] = 6
        }
    }
    println(nums.contentToString())
}


// Задание 14
val weapons = arrayOf(15, 40, 25, 10, 35)
var bossHealth = 150
var mana = 100

fun main(){
    var turn = 0
    while(bossHealth > 0){
        for(i in weapons){
            turn++
            if(mana < 20 ){
                println("Удар стоит 20 маны, у тебя $mana. Восстановление и пропуск хода")
                mana += 50
                continue
            }
            if(bossHealth < 0){
                break
            }
            else{
                bossHealth -= i
                mana -= 20
                println("Ты нанес $i урона и потратил 20 маны. Осталось $mana")
            }

        }
    }
    println("За $turn ходов мне удалось победить босса и опустить его здоровье до $bossHealth")
}