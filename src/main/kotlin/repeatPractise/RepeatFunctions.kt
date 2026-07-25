package repeatPractise

class RepeatFunctions {

// Функция принимает список целых цен (List<Int>) и порог (Int).
// Возвращает, сколько цен в списке строго меньше порога.

    fun countDiscounted(prices: List<Int>, limit: Int): Int {
        var result = 0
        for (price in prices) {
            if (price < limit) {
                result++
            }
        }
        return result
    }

    // Функция принимает List<Int> и возвращает наибольшую цену. Если список пуст — возвращает 0.
    fun maxPrice(prices: List<Int>): Int {
        var maxPrice = 0
        for (price in prices) {
            if (price > maxPrice) {
                maxPrice = price
            }
        }
        return maxPrice
    }

    // Принимает List<Int>, возвращает индекс наибольшего элемента. Пустой список → -1
    fun indexOfMax(items: List<Int>): Int {
        if (items.isEmpty()) {return -1}
        var maxIndex = 0
        for (i in items.indices) {
            if (items[i] > items[maxIndex]) maxIndex = i
        }
        return maxIndex
    }

    // пузырек
    fun bubbleSort(items: MutableList<Int>){
        for (j in 0 until items.size-1) {
            for (i in 0 until items.size - 1) {
                if (items[i] > items[i + 1]) {
                    val temp = items[i]
                    items[i] = items[i + 1]
                    items[i + 1] = temp
                }
            }
        }
    }
}
