package module_2_functions


fun rectangleInfo(width: Int, height: Int): String {
    fun calculateArea(width: Int, height: Int): Int = width * height
    fun calculatePerimeter(width: Int, height: Int): Int = 2 * (width + height)

    val area = calculateArea(width, height)
    val perimeter = calculatePerimeter(width, height)

    return "Площадь = $area, Периметр = $perimeter"
}


fun discountedTotal(prices: IntArray, discountPercent: Int) : Double {
    var total = 0.0
    fun applyDiscount(price: Int): Double = (price - (price * discountPercent / 100.0))
    for (price in prices) {
        total += applyDiscount(price)
    }
    return total
}


fun formatPhone(digits: String) : String {
    fun validatePhone(phone : String) : Boolean = phone.length == 11
    if (!validatePhone(digits)) return "Неверный формат"
    return "Номер принят $digits"
}

fun temperatureReport(temps: DoubleArray) : String {
    fun maxTemperature(temp: DoubleArray) : Double{
        var maxTemp = temps[0]
        for (i in temp){
            if (i > maxTemp){
                maxTemp = i
            }
        }
        return maxTemp
    }
    fun minTemperature(temp: DoubleArray) : Double{
        var minTemp = temps[0]
        for (i in temp){
            if (i<minTemp){
                minTemp = i
            }
        }
        return minTemp
    }
    fun avgTemperature(temp: DoubleArray) : Double{
        var avgTemp = 0.0
        for (i in temp){
            avgTemp += i
        }
        avgTemp /= temp.size
        return avgTemp
    }
    val outputLine = "Максимальная температура ${maxTemperature(temps)}, Минимадбная температура ${minTemperature(temps)}, средняя – ${avgTemperature(temps)}"
    return outputLine
}


fun gradeWithComment(score: Int) : String {
    fun gradeToChar(score: Int) : String {
        return when {
            score in 89..100 -> "A"
            score in 79..88 -> "B"
            score in 59..78 -> "C"
            else -> "D"
        }
    }
    fun gradeComment(grade: String): String = when (grade) {
        "A" -> "Отлично"
        "B" -> "Хорошо"
        "C" -> "Удовлетворительно"
        else -> "Плохо"
    }

    val letter = gradeToChar(score)
    val comment = gradeComment(letter)
    return "Оценка $letter, Комментарий $comment"
}


fun secondsToReadable(totalSeconds: Int): String {
    fun calculateHours(totalSeconds: Int) : Int = totalSeconds / 3600
    fun calculateMinutes(totalSeconds: Int) : Int = (totalSeconds % 3600) / 60
    fun calculateSeconds(totalSeconds: Int) : Int = totalSeconds % 60

    val hours = calculateHours(totalSeconds)
    val minutes = calculateMinutes(totalSeconds)
    val seconds = calculateSeconds(totalSeconds)
    return "Итого часов $hours, минут $minutes, секунд $seconds"

}


fun cartSummary(prices: IntArray, taxPercent: Int) : Double {

    fun calculatePrice(arr: IntArray) : Int {
        var total = 0
        for (p in arr) total += p
        return total
    }
    fun applyTax(sum: Int) : Double = sum + sum * taxPercent / 100.0

    val totalWithoutTax = calculatePrice(prices)
    val totalWithTax = applyTax(totalWithoutTax)
    return totalWithTax
}


fun passwordStrength(password: String) : String {
    fun isLongEnough(password: String) : Boolean = password.length >= 12
    fun hasDigit(password: String) : Boolean {
        for (c in password) {
            if(c.isDigit()) return true
        }
        return false
    }
    return if (isLongEnough(password) && hasDigit(password)) "Сильный" else "Слабый"
}

fun temperatureStatus(temps: DoubleArray) : String {
    fun avgTemperature(arr: DoubleArray) : Double{
        var avgTemp = 0.0
        for (p in arr) {
            avgTemp += p
        }
        return avgTemp / arr.size
    }

    val result = avgTemperature(temps)

    return when {
        result >= 25 -> "Жара $result"
        result >= 15 -> "Норм $result"
        else -> "Холодрыга $result"
    }
}

fun invoiceTotal(hours: Int, rate: Int, discount: Int) : String {
    fun calculateBaseRate(h: Int, r: Int) : Int = h * r
    val baseRate = calculateBaseRate(hours, rate)
    fun calculateDiscount(p: Int, d: Int) : Double = p - p * d / 100.0
    val total = calculateDiscount(baseRate, discount)

    return "Стоимость услуг со скидкой $total"
}


fun main() {
    println(rectangleInfo(100, 10))

    println(discountedTotal(intArrayOf(100, 299), 20))

    println(formatPhone("7911903962"))

    println(temperatureReport(doubleArrayOf(25.00, 30.00, 17.00)))

    println(gradeWithComment(49))

    println(secondsToReadable(7484))

    println(cartSummary(intArrayOf(100, 100), 20))

    println(passwordStrength("bingusTh3Cat"))

    println(temperatureStatus(doubleArrayOf(20.00, 25.00)))

    println(invoiceTotal(100, 20, 10))
}