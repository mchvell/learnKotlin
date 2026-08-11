package moduleFour

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Product(val id: Long, val name: String,
                   @SerialName("price_kopecks") val priceKopecks: Int,
                   val discount: Int? = null)

@Serializable
data class CatalogData( @SerialName("product_list") val products: List<Product>)

@Serializable
data class CatalogResponse(val state: Int, val data: CatalogData)