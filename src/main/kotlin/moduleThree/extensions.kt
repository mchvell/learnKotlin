package moduleThree

fun String.normalized(): String = lowercase().trim()

fun String.isErrorLine(): Boolean = "ERROR" in this

fun String.firstWord(): String = split(" ")[0]


data class DeliveryNew(val priceKopecks: Int, val isExpress: Boolean)

data class Position(val sku: String, val delivery: DeliveryNew)

data class Orders(val id: Long, val positions: List<Position>)


fun Orders.firstPosition(): Position = positions[0]
fun Orders.firstDeliveryPrice(): Int = firstPosition().delivery.priceKopecks
fun Orders.hasExpress(): Boolean = firstPosition().delivery.isExpress

val DeliveryNew.priceInRubles : Int
    get() = priceKopecks / 100

val Position.isFreeDelivery : Boolean
    get() = delivery.priceKopecks == 0

val Orders.positionsCount : Int
    get() = positions.size

fun String?.address(): String = this ?: "адрес не указан"
fun String?.hasAddress(): Boolean {
    if (this == null) return false
    return isNotBlank()
}

class Sku(val code: String) {
    fun describe() : String = "метод класса"
}

fun Sku.describe(): String = "расширение"

open class Node
class Leaf : Node()

fun Node.name() = "узел"
fun Leaf.name() = "лист"

