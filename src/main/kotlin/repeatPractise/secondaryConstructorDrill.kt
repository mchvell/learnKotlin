package repeatPractise

class PriceTag(val rubles: Int, val kopecks: Int){
    init {
        require(rubles >= 0) {"Рубли не могут быть отрицательными, $rubles"}
        require(kopecks in 0..99) {"Копейки должны быть в диапозоне от 0 до 99, $kopecks"}
    }

    constructor(total: Int) : this(total / 100, total % 100)
    constructor(kopecksStr: String) : this(kopecksStr.split(',')[0].trim().toInt(),
        kopecksStr.split(',')[1].trim().toInt())

    val totalKopecks: Int
        get() = rubles * 100 + kopecks

    fun format(): String = "$rubles руб. $kopecks коп."
}

class StockItem(val sku: String, val quantity: Int) {
    init {
        require(sku.isNotBlank()) {"СКУ не может быть пустым, $sku"}
        require(quantity >= 0) {"Кол-во не может быть отрицательным, $quantity"}
    }

    constructor(sku: String) : this(sku,0)

    val isOutOfStock: Boolean
        get() = quantity == 0
}

class Barcode(val prefix: String, val body: String, val checkDigit: Int) {
    init {
        require(prefix.isNotBlank()) {"Префикс не может быть пустым, $prefix"}
        require(body.isNotBlank()) {"Тело не может быть пустым, $body"}
        require(checkDigit in 0..9) {"Контрольная цифра должна быть от 0 до 9, $checkDigit"}
    }

    constructor(barcode: String) : this(barcode.split('-')[0].trim(),
        barcode.split('-')[1].trim(),
        barcode.split('-')[2].trim().toInt()
    )

    val full: String
        get() = "$prefix-$body-$checkDigit"

}

class Session(val userId: Long, val deviceId: String, val ttlMinutes: Int) {
    init {
        require(userId > 0) {"Айди пользователя строго > 0, $userId"}
        require(deviceId.isNotBlank()) {"Айди девайса не можнт быть пустым, $deviceId"}
        require(ttlMinutes in 1..1440) {"Время должно быть в интевале от 1 мин до 1440 мин, $ttlMinutes"}
    }

    constructor(userId: Long, deviceId: String) : this(userId, deviceId, 30)
    constructor(userId: Long) : this(userId, deviceId = "unknown")

    val isLong: Boolean
        get() = ttlMinutes > 60
}

