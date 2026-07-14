package moduleThree

/*
1. Класс Book
свойства: title (String), author (String), pages (Int)
метод info() → "«{title}» — {author}, {pages} стр."
создай объект, выведи info()
 */

class Book(val title: String, val author: String, val pages: Int) {
    fun info() = "Название: $title, Автор: $author, всего страниц: $pages"
}

/*
2. Класс Rectangle

свойства: width (Int), height (Int)
методы area() и perimeter()
создай объект, выведи оба
 */

class Rectangle(val width: Int, val height: Int) {
    fun area() = width * height
    fun perimeter() = (width + height) * 2
}

/*
3. Класс Product
свойства: name (String), price (Int), quantity (Int)
метод totalCost() → price × quantity
создай объект, выведи стоимость
 */

class Product(val title: String, val price: Int, val quantity: Int) {
    fun totalCost() = price * quantity
}

/*
4. Класс BankAccount
свойство: balance (Int, var — изменяемое)
методы deposit(amount) и withdraw(amount)
создай, сделай операции, выведи баланс
 */

class BankAccount(var balance: Int) {
    fun deposit(amount: Int) {
        balance += amount
    }
    fun withdraw(amount: Int){
        balance -= amount
    }
}

/*
5. Класс Student
свойства: name (String), grades (список оценок — List<Int>)
метод average() — возвращает средний балл (сумма / количество)
метод hasPassed() — возвращает Boolean (true если средний ≥ 60)
(тут в методе уже цикл/вычисление по списку)
 */

class Student(val name: String, val grades: List<Int>) {
    fun average() : Int {
        var result = 0
        for (grade in grades) {
            result += grade
        }
        return result / grades.size
    }

    fun hasPassed() : Boolean {
        if(average() >= 60){
            return true
        }
        return false
    }
}

/*
6. Класс ShoppingCart
свойство: items (список цен — MutableList<Int>, изменяемый)
метод addItem(price: Int) — добавляет цену в список
метод total() — возвращает сумму всех цен
метод count() — возвращает количество товаров
(состояние + методы меняющие его + вычисления)
 */

class ShoppingCart(val items: MutableList<Int>) {
    fun addItem(price: Int) {
        items.add(price)
    }

    fun count() = items.size

    fun total(): Int {
        var total = 0
        for(item in items){
            total += item
        }
        return total
    }
}



fun main() {
// 1
//    val book1 = Book("Три поросёнка", "Народная", 99)
//    val book2 = Book("Мастер и Маргарита", "Булгаков", 480)   // ДРУГАЯ книга!
//    book1.info()
//    book2.info()

// 2
//    val rectangle = Rectangle(10, 20)
//    println(rectangle.perimeter())
//    println(rectangle.area())

    // 3
//    val item = Product("Салфетки", 100, 10)
//    println(item.totalCost())

    //4
//    val account = BankAccount(1200)
//    account.withdraw(200)
//    account.deposit(50)
//    println(account.balance)

    // 5
//    val studentX = Student("Миша", listOf(100, 50, 60))
//    println("${studentX.name} набрал ${studentX.average()} и прошел?: ${studentX.hasPassed()}")

    val cart = ShoppingCart(mutableListOf())
    cart.addItem(100)
    cart.addItem(200)
    cart.addItem(300)

    println(cart.count())
    println(cart.total())

}

