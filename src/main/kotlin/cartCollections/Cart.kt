package cartCollections


class Cart(private val cartList: List<CartItem>) {

    val totalKopecks: Int
        get() = cartList.sumOf { it.priceKopecks * it.quantity }

    val inStockTotalKopecks: Int
        get() = cartList.filter { it.inStock }.sumOf { it.priceKopecks * it.quantity }

    val itemsNames: List<String>
        get() = cartList.map { it.name }

    val outOfStockNames: List<String>
        get() = cartList.filterNot { it.inStock }.map { it.name }

    val mostExpensiveNames: List<String>
        get() = cartList.sortedByDescending { it.priceKopecks }.map { it.name }.take(3)

    val isReady: Boolean
        get() = cartList.isNotEmpty() && cartList.all { it.inStock }

    fun hasCategory(category: String): Boolean = cartList.any { it.category == category }

    fun countInCategory(category: String): Int = cartList.count { it.category == category }

    fun mostExpensiveItem() : CartItem? = cartList.maxByOrNull { it.priceKopecks }

    val itemsByCategory: Map<String, List<CartItem>>
        get() = cartList.groupBy { it.category }

    val spentByCategory: Map<String, Int>
        get() = itemsByCategory.mapValues { entry -> entry.value.sumOf { it.priceKopecks * it.quantity } }

    val promoCodes: List<String>
        get() = cartList.mapNotNull { it.promoCode }.distinct()

    val receipt: String
        get() = cartList.joinToString {"${it.name}, x${it.quantity} – ${it.priceKopecks}"}

    val mostExpensiveDescription: String
        get() = mostExpensiveItem()?.let { "Самый дорогой: ${it.name} (${it.priceKopecks})" } ?: "Корзина пуста"

    fun priceOf(name: String): Int = cartList.find { it.name == name }?.run{this.priceKopecks * this.quantity} ?: 0
}
