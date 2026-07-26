package repeatPractise

/*
abstract class Product:

в конструктор: name: String, priceKopecks: Int, weightGrams: Int со значением по умолчанию 0;
init с проверками: имя не пустое, цена строго больше нуля, вес не отрицательный;
вычисляемое свойство priceRubles: Int — цена в рублях, целыми, без хранения;
открытый метод description(): String — возвращает "$name — $priceRubles ₽";
абстрактный category(): String.
 */

abstract class Product(val name: String, val priceKopecks: Int, val weightGrams: Int = 0) {
    init {
        require(name.isNotBlank()) { "Название товара не может быть пустым, $name" }
        require(priceKopecks > 0) { "Цена товара обязана быть положительной и выше нуля, $priceKopecks" }
        require(weightGrams >= 0) { "Вес не может быть отрицательным, $weightGrams" }
    }

    val priceRubles: Int
        get() = priceKopecks / 100

    open fun description(): String = "$name – $priceRubles ₽"

    abstract fun category(): String
}

/*
Наследники:
Book(name, priceKopecks, author: String) — вес фиксирован 300, категория "Книги",
description() переопределён: берёт результат родителя через super и дописывает ", автор: $author";
Fridge(name, priceKopecks, weightGrams) — категория "Техника", description() не трогает.
 */

class ArtBook(name: String, priceKopecks: Int, val author: String) : Product(name, priceKopecks, weightGrams = 300) {

    override fun description(): String {
        return super.description() + ", автор: $author"
    }

    override fun category(): String = "Книги"
}

class Freezer(name: String, priceKopecks: Int, weightGrams: Int) : Product(name, priceKopecks, weightGrams) {
    override fun category(): String = "Техника"
}
