package repeatPractise

class Coupon(val code: String, val discountPercent: Int) {
    init {
        require(code.isNotBlank()){"Купон не может быть пустным, $code"}
        require(discountPercent in 1..50){"Скидка не может быть 0 или больше 50%, $discountPercent"}
    }
}

class CartItem(val productName: String, val priceKopecks: Int, val quantity: Int = 1) {
    init {
        require(productName.isNotBlank()){"Название товара не может быть пустым, $productName"}
        require(priceKopecks > 0){"Цена не может быть отрицательной, $priceKopecks"}
        require(quantity in 1..999){"Количество товара не может быть отрицательно, ноль или выше 999, $quantity"}
    }
}

class WbOrder(val orderNumber: String, val totalKopecks: Int) {
    val currency = "RUB"
    val marketplace = "WB"

    init {
        require(orderNumber.isNotBlank()){"Номер заказа не может быть пустым, $orderNumber"}
        require(totalKopecks > 0){"Стоимость заказа строго положительна, $totalKopecks"}
    }
}

class Shipment(val weightGrams: Int, val isExpress: Boolean = false) {
    val carrier = "WB Logistics"

    init {
        require(weightGrams > 0){"Вес заказа строго положителен и больше нуля, $weightGrams"}
    }

    val daysToDeliver: Int
        get() = if (isExpress) 1 else 3

    val weightKg: Int
        get() = weightGrams / 1000
}

class Address(val city: String, val street: String, val house: String) {
    init {
        require(city.isNotBlank()){"Название города не может быть пустым, $city"}
        require(street.isNotBlank()){"Название улицы не может быть пустым, $street"}
        require(house.isNotBlank()){"Номер дома не может быть пустым, $house"}
    }

    constructor(raw: String) : this(
        raw.split(",")[0].trim(),
        raw.split(",")[1].trim(),
        raw.split(",")[2].trim()
    )

    val fullAddress: String
        get() = "$city, $street, $house"
}

abstract class ShipmentNew(val address: String, val weightGrams: Int, val carrier: String){
    init {
        require(address.isNotBlank()) {"Адрес не может быть пустым, $address"}
        require(weightGrams > 0) {"Вес строго положительный, $weightGrams"}
        require(carrier.isNotBlank()) {"Служба доставки не может быть пустой, $carrier"}
    }
}

class CourierDelivery(address: String, weightGrams: Int, carrier: String) : ShipmentNew(address, weightGrams, carrier)

class PickupPoint(address: String, weightGrams: Int): ShipmentNew(address, weightGrams, "WB")

abstract class PaymentMethod(val ownerName: String, val commissionPercent: Int, val provider: String) {
    init {
        require(ownerName.isNotBlank()) {"Имя владельца не может быть пустым, $ownerName"}
        require(commissionPercent in 0..10) {"Комиссия от 0 до 10, $commissionPercent"}
    }

    val hasCommission: Boolean
        get() = commissionPercent > 0

    open fun label(): String = "$provider, комиссия $commissionPercent%"

    abstract fun isInstant(): Boolean
}

class CardPayment(ownerName: String, commissionPercent: Int, provider: String) : PaymentMethod(ownerName, commissionPercent, provider) {
    override fun isInstant(): Boolean = false
}

class WbWallet(ownerName: String): PaymentMethod (ownerName, 0, "WB Кошелёк"){
    override fun label(): String = super.label() + " (без комиссии)"

    override fun isInstant(): Boolean = true
}

class Sbp(ownerName: String, commissionPercent: Int = 1): PaymentMethod(ownerName, commissionPercent, "СБП") {
    override fun isInstant(): Boolean = true
}

abstract class Refund(val orderNumber: String, val amountKopecks: Int, val reason: String){
    init {
        require(orderNumber.isNotBlank()) {"Номер заказа не может быть пустым, $orderNumber"}
        require(amountKopecks > 0) {"Стоимость заказа строго положительная, $amountKopecks"}
    }

    val amountRubles: Int
        get() = amountKopecks / 1000

    open fun summary(): String = "Возврат по заказу $orderNumber: $amountRubles ₽, причина: $reason"

    abstract fun needsInspection(): Boolean

}

class DefectRefund(orderNumber: String, amountKopecks: Int) : Refund(orderNumber, amountKopecks, "Брак"){
    override fun summary(): String {
        return super.summary() + ", требуется проверка"
    }

    override fun needsInspection(): Boolean = true
}

class SizeRefund(orderNumber: String, amountKopecks: Int, val size: String, reason: String = "Не подошел размер"):
    Refund(orderNumber, amountKopecks, reason) {
    override fun needsInspection(): Boolean = false
}

class CancelRefund(orderNumber: String, amountKopecks: Int):
    Refund(orderNumber, amountKopecks, "Отмена заказа") {
    override fun needsInspection(): Boolean = false
}

abstract class Supply(val supplierName: String, val itemsCount: Int, val warehouse: String) {
    init {
        require(supplierName.isNotBlank()) {"Имя поставщика не должно быть пустым, $supplierName"}
        require(itemsCount > 0) {"Количество товара строго положительное, $itemsCount"}
    }

    val isLarge: Boolean
        get() = itemsCount > 100

    open fun info(): String = "Поставка от $supplierName: $itemsCount шт., склад $warehouse"

    abstract fun requiresQualityCheck(): Boolean
}

class LocalSupply(supplierName: String, itemsCount: Int) : Supply(supplierName, itemsCount, "Коледино") {
    override fun requiresQualityCheck(): Boolean = false
}

class ImportSupply(supplierName: String, itemsCount: Int, val country: String, warehouse: String = "Импортный склад")
    : Supply(supplierName, itemsCount, warehouse) {
    override fun info(): String = super.info() + ", страна: $country"

    override fun requiresQualityCheck(): Boolean = true

}

class ReturnSupply(supplierName: String, itemsCount: Int)
    : Supply(supplierName, itemsCount, "Возвратный") {
    override fun requiresQualityCheck(): Boolean = true

    val boxesCount: Int
        get() = itemsCount / 20

}