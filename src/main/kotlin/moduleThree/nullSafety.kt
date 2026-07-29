package moduleThree

data class ProductResponse(
    val id: Long,
    val priceKopecks: Int,
    val discountPercent: Int?,
    val promoText: String?
) {
    fun finalPrice(): Int {
        val percent = discountPercent ?: 0
        return priceKopecks - priceKopecks * percent / 100
    }

    fun promoLabel(): String = promoText?.uppercase() ?: "Без акции"

    fun promoLength(): Int? {
        return promoText?.length
    }
}

data class DeliveryInfo(
    val address: String?,
    val courierName: String?,
    val etaMinutes: Int?
) {
    fun shortAddress(): String? = address?.split(" ")?.get(0)

    fun courierGreeting(): String = courierName?.let {"Курьер $it уже в пути"} ?: "Курьер не назначен"

    fun etaLabel(): String = if(etaMinutes == null) "Время не известно" else "$etaMinutes минут"

    fun isSoon(): Boolean = etaMinutes != null && etaMinutes < 15
}

class PromoInput(val rawCode: String, val rawQuantity: String) {

    fun quantity(): Int = rawQuantity.toIntOrNull() ?: 1

    fun code(): String? = rawCode.takeIf { it.isNotBlank() }?.uppercase()

    fun isValid(): Boolean = rawCode.isNotBlank() && (rawQuantity.toIntOrNull() ?: 0) > 0
}

data class Courier(val name: String, val phone: String?)

data class Delivery(val courier: Courier?, val address: String)

data class CustomerOrder(val id: Long, val delivery: Delivery?) {

    fun courierName(): String? = delivery?.courier?.name

    fun courierPhone(): String = delivery?.courier?.phone ?: "телефон не указан"

    fun hasCourier(): Boolean = delivery?.courier != null
}

class Catalog(val products: List<CustomProduct>) {

    fun findById(id: Long): CustomProduct? = products.find {it.id == id}

    fun nameById(id: Long): String = findById(id)?.name ?: "товар не найден"

    fun cheapest(): CustomProduct? = products.minByOrNull { it.priceKopecks }

    fun cheapestPrice(): Int = cheapest()?.priceKopecks ?: 0
}

data class CustomProduct(val id: Long, val name: String, val priceKopecks: Int)

class UserProfile(val nickname: String?) {

    fun greeting(): String = nickname?.let { "Привет ${it}!" } ?: "Привет, гость"
}

class Review(val comment: String?) {

    fun commentLength(): Int = comment?.length ?: 0
    fun hasComment(): Boolean = comment?.isNotBlank() ?: false
}

class ProductCard(val name: String, val brand: String?, val discountPercent: Int?) {

    fun title(): String = brand?.let { "$it $name" } ?: name
    fun discountLabel(): String = discountPercent?.let { "Скидка $it %" } ?: "Без скидки"
    fun hasDiscount(): Boolean = discountPercent?.let { it > 0 } ?: false
}

data class Passport(val number: String, val issuedBy: String?)

data class Client(val name: String, val passport: Passport?) {

    fun passportNumber(): String? = passport?.number

    fun issuer(): String = passport?.issuedBy ?: "не указано"

    fun hasPassport(): Boolean = passport?.number?.isNotBlank() ?: false
}

class OrderForm(val rawPhone: String, val rawAmount: String) {

    fun phone(): String? = rawPhone.trim().takeIf {it.isNotBlank()}
    fun amount(): Int = rawAmount.trim().toIntOrNull() ?: 0
    fun isReady(): Boolean = phone() != null && amount()  > 0
}

class DeliverySlot(val date: String?, val timeFrom: String?, val timeTo: String?) {

    fun label(): String {
        if (date == null || timeFrom == null || timeTo == null) return "слот не выбран"
        return "$date, $timeFrom-$timeTo"
    }

    fun isFullDay(): Boolean = timeFrom == "00:00" && timeTo == "23:59"

    fun hasDate(): Boolean = date != null
}

abstract class Notification(val title: String, val body: String?) {
    init {
        require(title.isNotBlank()) { "Title cannot be blank" }
    }

    val hasBody: Boolean
        get() = body?.isNotBlank() ?: false

    open fun format(): String {
        if (body == null) return title
        return "$title: $body"
    }

    abstract fun channel(): String
}


class PushNotification(title: String) : Notification(title, null) {
    override fun channel(): String = "push"
}

class EmailNotification(title: String, body: String, val subject: String) : Notification(title, body) {
    override fun channel(): String = "email"
    override fun format(): String {
        return super.format() + " [$subject]"
    }
}

data class OrderResponse(
    val id: Long,
    val totalKopecks: Int,
    val discountKopecks: Int?,
    val isExpress: Boolean?,
    val promoCode: String?
) {
    fun effectiveDiscount(): Int = discountKopecks ?: 0
    fun express(): Boolean = isExpress ?: false
    fun finalPrice(): Int = totalKopecks - effectiveDiscount()
    fun summary(): String {
        if (promoCode != null) return "Заказ $id: ${finalPrice()} коп., промокод: $promoCode"
        return "Заказ $id: ${finalPrice()} коп."
    }
}

fun main() {
    val x = OrderResponse(1, 2000, 50, null, null)
    println(x.summary())
}