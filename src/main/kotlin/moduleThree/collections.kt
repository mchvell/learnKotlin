package moduleThree


data class ProductData(
    val name: String,
    val category: String,
    val priceKopecks: Int,
    val inStock: Boolean
)

val socks = ProductData("Носки", "Одежда", 39900, true)
val watch = ProductData("Часы","Украшения", 1000000, true)
val sneakers = ProductData("Кроссовски", "Обувь", 750000, false)
val loafers = ProductData("Лофферы", "Обувь", 2000000, true)
val gpu = ProductData("Ryzen 7 9800 x3d", "Электроника", 55000000, false)

val items = listOf(socks, watch, sneakers, loafers, gpu)

//fun main() {
//    val inStock = items.filter { it.inStock }.map { it.name }
//    println(inStock)
//
//    val totalPriceItemsInStock = items.filter {it.inStock}.sumOf { it.priceKopecks }
//    println(totalPriceItemsInStock)
//
//    val namesOfMostExpensive = items.filter { it.inStock }.sortedByDescending { it.priceKopecks }.take(2).map { it.name }
//    println(namesOfMostExpensive)
//}