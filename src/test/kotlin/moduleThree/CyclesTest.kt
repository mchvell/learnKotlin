package moduleThree

import repeatPractise.RepeatFunctions
import kotlin.test.Test
import kotlin.test.assertEquals


class CyclesTest {

    @Test
    fun countValuesLowerThanLimit(){
        val prices: List<Int> = listOf(100, 200, 300, 400)
        val result = RepeatFunctions().countDiscounted(prices, 200)
        assertEquals(1, result)
    }

    @Test
    fun findMaxPriceInList(){
        val prices: List<Int> = listOf(100, 200, 300, 400)
        val result = RepeatFunctions().maxPrice(prices)
        assertEquals(400, result)
    }

    @Test
    fun findMaxIndexInList(){
        val prices: List<Int> = listOf(100, 200, 300, 400)
        val result = RepeatFunctions().indexOfMax(prices)
        assertEquals(3, result)
    }

    @Test
    fun orderOfList(){
        val items = mutableListOf(3, 2, 1)
        RepeatFunctions().bubbleSort(items)
        val sorted = listOf(1,2,3)
        assertEquals(sorted, items)
    }
}

