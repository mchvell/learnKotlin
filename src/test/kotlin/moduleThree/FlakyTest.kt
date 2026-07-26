package moduleThree

import repeatPractise.FlakyTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class FlakyTestCase {

    @Test
    fun flakyPassOnFirstAttempt() {
        val result = FlakyTest(3, 1).runOnce()
        assertTrue(result)
    }

    @Test
    fun flakyDoesntPassOnSecondAttempt() {
        val result = FlakyTest(2, 2).runOnce()
        assertFalse(result)
    }

    @Test
    fun flakyRunWithRetry(){
        val result = FlakyTest(3, 2).runWithRetry()
        assertTrue(result)
    }

    @Test
    fun successOnHigherThanAttempt(){
        val result = FlakyTest(3, 10).runWithRetry()
        assertFalse(result)
    }
}