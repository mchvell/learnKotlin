package cartTest

import cartCollections.Cart
import cartCollections.CartItem
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals


class CartTest {

    private val watch = CartItem("Oris BC", "Украшения", 250_000_00, 1, true, "BIGDEAL")
    private val ball = CartItem("Мяч Adidas", "Спортивный инвентарь", 5000_00, 3, true, "WC2026")
    private val wine = CartItem("Urban Riesling", "Алкогольные напитки", 3000_00, 10, false, "WINE25")
    private val coffee = CartItem("Кофе молотый", "Кофе и чай", 499_00, 1, true, null)
    private val graphicCard = CartItem("NVIDIA 5070", "Электроника", 150_000_00, 2, true, "GAMER")

    private val ring = CartItem("Кольцо Cartier", "Украшения", 390_000_00, 2, true, "BIGDEAL")

    @Test
    fun totalKopecksIsEqual() {
        val items = mutableListOf(wine, coffee, graphicCard).apply { this.add(watch)
            this.add(ball)}
        val cart = Cart(items)
        assertEquals(59_549_900, cart.totalKopecks)
    }

    @Test
    fun inStockTotalKopecksEqual() {
        val items = mutableListOf(coffee, graphicCard).apply { this.add(watch)}
        val cart = Cart(items)
        assertEquals(550_499_00, cart.inStockTotalKopecks)
    }

    @Test
    fun itemsNamesEqual() {
        val items = mutableListOf(coffee).apply { this.add(wine)}
        val cart = Cart(items)
        val expectedNames = listOf("Кофе молотый", "Urban Riesling")
        assertEquals(expectedNames, cart.itemsNames)
    }

    @Test
    fun outOfStockNamesEqual() {
        val items = mutableListOf(graphicCard).apply { this.add(wine)}
        val cart = Cart(items)
        val expectedNames = listOf("Urban Riesling")
        assertEquals(expectedNames, cart.outOfStockNames)
    }

    @Test
    fun mostExpensiveNamesEquals() {
        val items = mutableListOf(watch, ball).apply { this.add(wine)
        this.add(coffee)
        this.add(graphicCard)}
        val cart = Cart(items)
        val expectedNames = listOf("Oris BC", "NVIDIA 5070", "Мяч Adidas")
        assertEquals(expectedNames, cart.mostExpensiveNames)
    }

    @Test
    fun cartIsReadyForPurchase() {
        val items = mutableListOf(watch, ball).apply { this.add(coffee) }
        val cart = Cart(items)
        assertTrue(cart.isReady)
    }

    @Test
    fun cartIsNotReadyForPurchase() {
        val items = mutableListOf(watch).apply { this.add(wine) }
        val cart = Cart(items)
        assertFalse(cart.isReady)
    }

    @Test
    fun categoryMatches() {
        val items = mutableListOf(watch, ball).apply { this.add(coffee) }
        val cart = Cart(items)
        assertTrue(cart.hasCategory("Украшения"))
    }

    @Test
    fun categoriesDoesNotMatch() {
        val items = mutableListOf(ball).apply { this.add(coffee) }
        val cart = Cart(items)
        assertFalse(cart.hasCategory("Украшения"))
    }

    @Test
    fun countInCategoryEqual() {
        val items = mutableListOf(watch, graphicCard).apply { this.add(ring) }
        val cart = Cart(items)
        assertEquals(2, cart.countInCategory("Украшения"))
    }

    @Test
    fun countInCategoryNotEqual() {
        val items = mutableListOf(watch).apply { this.add(coffee) }
        val cart = Cart(items)
        assertNotEquals(2, cart.countInCategory("Украшения"))
    }

    @Test
    fun mostExpensiveItemIsRing() {
        val items = mutableListOf(watch, graphicCard).apply { this.add(ring) }
        val cart = Cart(items)
        assertEquals(ring, cart.mostExpensiveItem())
    }

    @Test // понять как объявить Map<String, List<CartItem>>
    fun itemsByCategoryMatches() {
        val items = mutableListOf(watch, coffee).apply { this.add(wine) }
        val cart = Cart(items)
    }

    // TODO spentByCategory test

    @Test
    fun promoCodesList() {
        val items = mutableListOf(watch, graphicCard).apply { this.add(coffee)
        this.add(ring)}
        val cart = Cart(items)
        val expected = listOf("BIGDEAL", "GAMER")
        assertEquals(expected, cart.promoCodes)
    }

    @Test
    fun receiptIsEqual() {
        val items = mutableListOf(watch).apply { this.add(coffee) }
        val cart = Cart(items)
        val expected = "Oris BC, x1 – 25000000, Кофе молотый, x1 – 49900"
        assertEquals(expected, cart.receipt)
    }

    @Test
    fun mostExpensiveDescriptionIsEqual() {
        val items = mutableListOf(watch).apply { this.add(coffee) }
        val cart = Cart(items)
        val expected = "Самый дорогой: Oris BC (25000000)"
        assertEquals(expected, cart.mostExpensiveDescription)
    }

    @Test
    fun mostExpensiveDescriptionIsNull() {
        val items = emptyList<CartItem>()
        val cart = Cart(items)
        val expected = "Корзина пуста"
        assertEquals(expected, cart.mostExpensiveDescription)
    }

    @Test
    fun priceOfCartEqual() {
        val items = mutableListOf(watch, ball).apply { this.add(wine) }
        val cart = Cart(items)
        val expected = 30000_00
        assertEquals(expected, cart.priceOf("Urban Riesling"))
    }
}