package moduleFour

import moduleFour.scheduler.Scheduler
import moduleFour.scheduler.TestCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class SchedulerTest {

    private lateinit var scheduler: Scheduler
    private val testCaseOne = TestCase("Оформление товара",
        "Регресс", 799, listOf("Critical", "Без флага"))
    private val testCaseTwo = TestCase("Отмена заказа",
        "Регресс", 400, listOf("Blocker", "C флагом"))
    private val testCaseThree = TestCase("Цена в кнопке",
        "Селективный", 300, listOf("Normal"))
    private val testCaseFour = TestCase("Смена адреса",
        "Регресс", 100, listOf("Blocker", "Без флага"))
    private val testCaseFive = TestCase("Оформление подписки",
        "Селективный", 1000)

    @BeforeEach
    fun setup() {
        scheduler = Scheduler(listOf(testCaseOne, testCaseTwo,
            testCaseThree, testCaseFour, testCaseFive))
    }

    @Test
    @DisplayName("Самый долги кейс совпадает с ожидаемым")
    fun schedulerContainsTestCases() {
        assertEquals(testCaseFive, scheduler.slowest)
    }

    @Test
    @DisplayName("Список тегов совпадает с ожидаемым")
    fun tagsAreEqual() {
        val expected = listOf(testCaseTwo, testCaseFour)
        assertEquals(expected, scheduler.byTag("Blocker"))
    }

    @Test
    @DisplayName("Список кейсов длинее 799 совпадает с ожидаемым")
    fun longerThan799() {
        val expected = listOf(testCaseFive)
        assertEquals(expected, scheduler.longerThan(799))
    }

    @Test
    @DisplayName("Сумма мс совпадает с ожидаемой")
    fun totalMsIsCorrect() {
        assertEquals(2599, scheduler.totalMs)
    }

    @Test
    @DisplayName("Инвариативная проверка совпадает с ожидаемой")
    fun distributeKeepsAllCases() {
        val buckets = scheduler.distribute(2)
        val resultTotalMs = buckets.sumOf { it.totalMs }
        val resultTotalCases = buckets.sumOf { it.size }
        assertAll(
            {assertEquals(2599, resultTotalMs)},
            {assertEquals(2, buckets.size) },
            {assertEquals(5, resultTotalCases) }
        )
    }

    @ParameterizedTest
    @ValueSource(ints = [3,4,5])
    @DisplayName("Кол-во создаваемых бакетов совпадает с ожидаемым")
    fun amountOfBucketsAreExpected(size: Int) {
        val buckets = scheduler.distribute(size)
        assertEquals(size, buckets.size)
    }

    @ParameterizedTest
    @CsvSource("2, 1", "3, 201")
    @DisplayName("Спред по бакетам ожидаем")
    fun bucketSpreadMs(size: Int, expectedSpread: Int) {
        val buckets = scheduler.distribute(size).spreadMs
        assertEquals(expectedSpread, buckets)
    }

    @ParameterizedTest
    @MethodSource("testCases")
    @DisplayName("Проверка статики тест-кейса")
    fun testCaseOne(testCase: TestCase) {
        assertAll(
            { assertTrue(testCase.name.isNotBlank()) },
            { assertTrue(testCase.durationMs >= 0) }
        )

    }

    companion object {
        @JvmStatic
        fun testCases() = listOf(
            TestCase(
                "Покупка товара",
                "Регрессия",
                1500,
            ),
            TestCase(
                "Отмена заказа",
                "Смоук",
                1900,
            )
        )
    }


}