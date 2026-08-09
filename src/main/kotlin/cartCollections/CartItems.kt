package cartCollections

data class CartItem(
    val name: String,
    val category: String,
    val priceKopecks: Int,
    val quantity: Int,
    val inStock: Boolean,
    val promoCode: String?
)