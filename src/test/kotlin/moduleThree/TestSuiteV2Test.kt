package moduleThree

import kotlin.test.assertEquals
import kotlin.test.Test
import kotlin.test.assertFailsWith

class TestData{
    val total = 100
    val failed = 10
    val passRate = 90
}


class TestSuiteV2Test{


    val data = TestData()

    private fun makeSuite() = TestSuiteV2(data.total, data.failed)

    // создание с валидными значениями работает — поля равны переданным
    @Test
    fun createTestSuite(){
        val result = makeSuite()
        assertEquals(data.total,result.totalTests)
        assertEquals(data.failed,result.failedTests)
    }

    // passRate считается верно — подбери числа, где ответ очевиден глазами
    @Test
    fun checkPassRate(){
        val result = makeSuite()
        assertEquals(data.passRate,result.passRate)
    }

    // update с failed больше total отбрасывается — assertFailsWith
    @Test
    fun checkFailWithExceptionOnUpdateAndValidateStateAfter(){
        val result = makeSuite()
        assertFailsWith<IllegalArgumentException> {
            result.update(10, 30)
        }
        assertEquals(data.total,result.totalTests)
        assertEquals(data.failed,result.failedTests)
    }

    @Test
    fun checkUpdate(){
        val result = makeSuite()
        result.update(10, 5)
        assertEquals(10,result.totalTests)
        assertEquals(5,result.failedTests)
        assertEquals(50, result.passRate)

    }

    // создание с failed больше total падает — init-защита, assertFailsWith
    @Test
    fun checkFailWithExceptionOnClass(){
        assertFailsWith<IllegalArgumentException> {
            TestSuiteV2(data.failed, data.total)
        }
    }

}