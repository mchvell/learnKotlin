package repeatPractise

interface Retryable{
    val attempts: Int

    fun runOnce(): Boolean

    fun runWithRetry(): Boolean {
        for (i in 1..attempts) {
            if (runOnce()) {
                return true
            }
        }
        return false
    }
}

class FlakyTest(override val attempts: Int, val successOn: Int): Retryable{
    private var currentAttempts = 0

    override fun runOnce(): Boolean {
        currentAttempts++
        return currentAttempts == successOn
    }
}

interface Discountable{
    val basePrice: Int
    fun discountPercent(): Int
    fun finalPrice(): Int = basePrice - basePrice * discountPercent() / 100
}

interface Shippable{
    val weightGrams: Int
    fun isHeavy(): Boolean {
        return weightGrams > 5000
    }
}

class Book(override val basePrice: Int, override val weightGrams: Int): Discountable, Shippable {
    override fun discountPercent(): Int = 10
}

class Fridge(override val basePrice: Int, override val weightGrams: Int): Discountable, Shippable {
    override fun discountPercent(): Int = if(basePrice > 100000)  5 else  0
}