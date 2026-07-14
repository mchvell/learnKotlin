package moduleOne

fun rangePrint(){
    for(i in 1..10){
        print("$i, ")
    }
}

fun square(){
    for (i in 1..7){
        println(i * i)
    }
}

val prices = listOf(100, 250, 75, 430, 190)

fun pricePrint(){
    for(i in prices){
        println("Цена: $i руб")
    }
}

fun cube(){
    for(i in 1..9){
        println("$i * 3 = " + i * 3 )
        }
    }

fun downTo(){
    for(i in 10 downTo 1){
        println(i)
    }
}

fun isEven(){
    for(i in 1..20){
        if(i%2==0){
            println("$i – четное")
        }
        else{
            println("число $i не четное")
        }
    }
}

val orders = listOf(150, 3200, 890, 5400, 320, 1100)

fun isExpensive(){
    val expensiveOrder = 1000
    for(i in orders){
        val label = if(i > expensiveOrder) "Крупный заказ: " else "Обычный заказ: "
        println("$label: $i")
    }
    for (i in orders){
        if(i > expensiveOrder){
            println("Крупный заказ: $i ")
        }
        else{
            println("Обычный заказ: $i ")
        }
    }
}

fun FizzBuzz(){
    for (i in 1..15){
        val devision = if((i % 3 == 0) and (i % 5 == 0)) "FizzBuzz " else if(i % 3 == 0) "Fizz "
            else if (i % 5 == 0)  "Buzz " else i
        print("$devision ")
    }
}

