package moduleFour

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.json.Json

class SerializableHelpersTest {

    private val catalogJson = """
{
  "state": 0,
  "data": {
    "product_list": [
      {"id": 1, "name": "Кофе", "price_kopecks": 49900, "discount": 10},
      {"id": 2, "name": "Мяч", "price_kopecks": 500000},
      {"id": 3, "name": "Вино", "price_kopecks": 149900, "discount": 0},
      {"id": 4, "name": "Чай", "price_kopecks": 89900, "discount": 25},
      {"id": 5, "name": "Часы", "price_kopecks": 2500000, "discount": 5}
    ]
  }
}
"""

    private val shortCatalogJson = """{
  "state": 0,
  "data": {
    "product_list": [
      {"id": 1, "name": "Кофе", "price_kopecks": 49900, "discount": 10},
       {"id": 2, "name": "Мяч", "price_kopecks": 500000}
    ]
  }
}
"""

    private val coffee = Product(1, "Кофе", 49900, 10)
    private val ball = Product(2, "Мяч", 500000)
    private val tea = Product(4, "Чай", 89900, 25)
    private val watch = Product(5, "Часы", 2500000, 5)



    @Test
    fun serializeProducts() {
        val result = Json.decodeFromString<CatalogResponse>(shortCatalogJson)

        val expected = CatalogResponse(0, CatalogData(listOf(coffee, ball)))
        assertEquals(expected, result)
    }

    @Test
    fun itemsNamesAreEqual() {
        val result = CatalogData(listOf(coffee, ball)).productNames
        val expected = listOf("Кофе", "Мяч")
        assertEquals(expected, result)
    }

    @Test
    fun emptyCatalogNotContainsNames() {
        val result = CatalogData(emptyList()).productNames
        val expected = emptyList<String>()
        assertEquals(expected, result)
    }

    @Test
    fun emptyCatalogNotContainsPrice() {
        val result = CatalogData(emptyList()).totalPrice
        assertEquals(0,result)
    }

    @Test
    fun discountItemsAreEqual() {
        val result = CatalogData(listOf(coffee, ball, tea)).discountItems
        val expected = listOf(coffee, tea)
        assertEquals(expected, result)
    }

    @Test
    fun mostExpensiveProduct() {
        val result = CatalogData(listOf(coffee, ball, watch)).mostExpensiveProduct
        val expected = watch
        assertEquals(expected, result)
    }

    @Test
    fun descriptionIsEqual() {
        val result = CatalogData(listOf(coffee, ball, tea)).description
        val expected = "Каталог: Кофе, Мяч, Чай, 3 товара, самый дорогой товар: Мяч (500000)"
        assertEquals(expected, result)
    }

    @Test
    fun descriptionIsEmpty() {
        val result = CatalogData(emptyList()).description
        assertEquals("Каталог пуст", result)
    }

}