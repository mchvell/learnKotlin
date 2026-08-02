package moduleThree

enum class OrderStatus(val label: String, val isFinal: Boolean) {
    NEW("Новый", false),
    PAID("Оплачен", false),
    SHIPPED("Отправлен", false),
    DELIVERED("Доставлен", true),
    CANCELLED("Отменён", true);

    fun canCancel(): Boolean = !isFinal
}

fun statusMessage(status: OrderStatus): String {
    return when (status) {
        OrderStatus.NEW -> "Новый заказ"
        OrderStatus.PAID -> "Заказ оплачен"
        OrderStatus.SHIPPED -> "Отправлен продавцом"
        OrderStatus.DELIVERED -> "Доставлен покупателю"
        OrderStatus.CANCELLED -> "Отменен по заявке"
    }
}

fun parseStatus(raw: String?): OrderStatus? {
    return when(raw) {
        "NEW" -> OrderStatus.NEW
        "PAID" -> OrderStatus.PAID
        "SHIPPED" -> OrderStatus.SHIPPED
        "DELIVERED" -> OrderStatus.DELIVERED
        "CANCELLED" -> OrderStatus.CANCELLED
        else -> null
    }
}

fun labelOrDefaultF(raw: String?): String = parseStatus(raw)?.label ?: "неизвестен"


enum class Priority(val label: String) {
    LOW("Низкий"),
    MEDIUM("Средний"),
    HIGH("Высокий")
}

fun isUrgent(p: Priority): Boolean = p == Priority.HIGH

fun labelOf(priority: Priority): String = priority.label

fun message(p: Priority): String = when(p) {
    Priority.LOW, Priority.MEDIUM -> "Можно забить"
    Priority.HIGH -> "Забить нельзя"
}

fun parse(raw: String): Priority? = when(raw.trim()) {
    "LOW" -> Priority.LOW
    "MEDIUM " -> Priority.MEDIUM
    "HIGH" -> Priority.HIGH
    else -> null
}

fun labelOrDefaultR(raw: String): String = parse(raw)?.label ?: "не известно"

