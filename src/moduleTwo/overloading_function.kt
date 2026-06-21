package moduleTwo

/*
Заданиие 1
 Напиши три версии функции greet:
 одна принимает имя (String) → "Привет, {имя}!",
 вторая принимает имя и возраст (String, Int) → "Привет, {имя}, тебе {возраст}!",
 третья без параметров → "Привет, гость!"
 */

fun greet(name: String): String {
    return "Привет $name!"
}

fun greet(name: String, age: Int): String {
    return "Привет $name, тебе $age!"
}

fun greet(): String{
    return "Привет, гость"
}

/*
Задание 2
Напиши две версии area:
для квадрата area(side: Int) → side²,
для прямоугольника area(width: Int, height: Int) → width × height.
 */

fun area(side: Int): String {
    return "Площадь квадрата со стороной $side = ${side * side}"
}

fun area(side: Int, height: Int): String {
    return "Площадь прямоугольника с длинной стороны $side и шириной $height = ${side * height}"
}

/*
Задание 3
 Напиши две версии format:
 format(price: Int) → "Цена: {price} ₽", format(price: Double) → "Цена: {price} $"
 */

fun format(price: Int): String {
    return "Цена $price ₽"
}

fun format(price: Double): String {
    return "Цена $price $"
}

fun main(){
    // println(greet("Миша", 19))
    // println(area(2, 5))
    println(format(10.0))
}