package moduleThree

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse



class StatusTest {

    @Test
    fun needRerunForFailed() {
        val result = TestStatus.FAILED.needsRerun()
        assertTrue(result)
    }

    @Test
    fun noNeedRerunForBlocked() {
        val result = TestStatus.BLOCKED.needsRerun()
        assertFalse(result)
    }

    @Test
    fun lineAsExpected() {
        val result = line("Оформление заказа", TestStatus.FAILED)
        val expected = "✗ Оформление заказа"
        assertEquals(expected, result)
    }

    @Test
    fun isVerdictCorrect() {
        val results = listOf(verdict(TestStatus.PASSED),
            verdict(TestStatus.FAILED),
            verdict(TestStatus.SKIPPED),
            verdict(TestStatus.BLOCKED))

        val expected = listOf("Успешно пройден",
            "Зафейлен, перепройти",
            "Пропущен, определить причину",
            "Тест заблокирован, разобрать")

        assertEquals(expected, results)
    }

    @Test
    fun needRerunIsProblem() {
        val statuses = TestStatus.entries
        for (status in statuses) {
            if (status.needsRerun()) assertTrue { status.isProblem }
        }
    }


}