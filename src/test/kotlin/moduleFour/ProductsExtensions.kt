package moduleFour

val CatalogData.productNames: List<String>
    get() = products.map { it.name }

val CatalogData.totalPrice: Int
    get() = products.sumOf { it.priceKopecks }

val CatalogData.discountItems: List<Product>
    get() = products.filter { it.discount != null && it.discount > 0 }

val CatalogData.mostExpensiveProduct: Product?
    get() = products.maxByOrNull { it.priceKopecks }

val CatalogData.description: String
    get() = mostExpensiveProduct?.let { "Каталог: ${productNames.joinToString(", ")}, " +
            "${products.size} товара, самый дорогой товар: ${it.name} (${it.priceKopecks})" } ?: "Каталог пуст"