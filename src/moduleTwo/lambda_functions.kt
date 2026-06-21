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

/*
Задание 9
Есть val numbers = listOf(3, 17, 8, 24, 5, 19). Через filter оставь только числа меньше 10
 */

val numbers = listOf(3, 17, 8, 24, 5, 19)
val newList = numbers.filter{it < 10}

/*
Задание 10
Есть val words = listOf("kotlin", "java", "python").
Через map преврати каждое слово в его длину (количество символов — it.length). Результат — список чисел.
 */

val words = listOf("kotlin", "java", "python")
val wordsLength = words.map{it.length}


/*
Задание 11
Есть val prices = listOf(1200, 800, 3000, 450).
Через any проверь есть ли хоть один товар дороже 2500
 */

val itemPrices = listOf(1200, 800, 3000, 450)
val isExpensive = itemPrices.any{it > 2500}


/*
Задание 12
 Есть val temps = listOf(18, 22, 30, 15, 27, 12).
 Через filter оставь температуры в диапазоне 18..27 (используй в лямбде && или in)
 */

val newTemps = listOf(18, 22, 30, 15, 27, 12)
val tempsDiapason = newTemps.filter{it >= 18 && it <= 27}

/*
Задание 13
 Есть val names = listOf("anna", "BOB", "Kate").
 Через map приведи все имена к нижнему регистру (it.lowercase()),
 потом через filter оставь те что начинаются на "a" (it.startsWith("a")). (можно в цепочку: .map{}.filter{})
 */

val newNames = listOf("anna", "BOB", "Kate")
val lowerCase = newNames.map{it.lowercase()}.filter{it.startsWith('a')}

/*
Задание 14
 Есть val nums = listOf(45, 12, 78, 33, 90, 5).
 Получи список только из тех чисел, что делятся на 3 без остатка
 */

val newNums = listOf(45, 12, 78, 33, 90, 5)
val dividedByThree = newNums.filter{it % 3 == 0}


/*
Задание 15
 Есть val names = listOf("misha", "anna", "bob").
 Получи список где каждое имя превращено в строку "Привет, {имя}!"
 */

val listOfNames = listOf("misha", "anna", "bob")
val greetings = listOfNames.map{"Привет $it"}

/*
Задание 16
Есть val ages = listOf(15, 22, 17, 30, 16).
Проверь, есть ли среди них хоть один несовершеннолетний (меньше 18). Ответ — да/нет
 */

val ages = listOf(15, 22, 17, 30, 16)
val isMinor = ages.any{it < 18}

/*
Задание 18
 Есть val prices = listOf(100, 250, 80, 500, 30).
 Проверь, все ли товары дешевле 1000. Ответ — да/нет
 */

val productPrices = listOf(100, 2500, 80, 500, 30)
val cheaperThan = productPrices.all{it < 1000}

/*
 Есть val words = listOf("kotlin", "go", "rust", "java", "c").
 Получи список длин только тех слов, что длиннее 2 символов. (тут нужно подумать про комбинацию)
 */

val programmingLanguages = listOf("kotlin", "go", "rust", "java", "c")
val higherThanTwo = programmingLanguages.filter {it.length > 2}.map {it.length}


fun main(){
     println("При умножении мы получили ${number(7)}")
     println(greet("Миша"))
     println(isEven(6))
     println(higherThanTen)
     println(applyTax)
     println(isEvenNew)
     println(upperCase)
     println(upperTwentyFive)
     println(newList)
     println(wordsLength)
     println("Существует товар дороже 2500: $isExpensive")
     println(tempsDiapason)
     println(lowerCase)
     println(dividedByThree)
     println(greetings.joinToString("\n"))
     println(isMinor)
    println(cheaperThan)
    println(higherThanTwo)
}

