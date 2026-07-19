package moduleThree

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals


class RetryableTest {

    // проверяем что в аутпуте содержатся значения из класса ApiHealthCheck
    @Test
    fun checkApiHealthExecute(){
        val result = ApiHealthCheck().runWithRetry()
        assertContains(result, "попыток: 3")
        assertContains(result, "ping OK")
    }

    @Test
    fun checkDbMigrationExecute(){
        val result = DbMigration().runWithRetry()
        assertContains(result, "попыток: 1")
        assertContains(result, "миграция применена")
    }

    @Test
    fun checkApiAndDbChain(){
        val results: List<Retryable> = listOf(ApiHealthCheck(), DbMigration(), BackUpTask(), SmokeTest())
        for (result in results){
            assertContains(result.runWithRetry(), "[попыток:")
        }
    }

    @Test
    fun checkBackup(){
        val result = BackUpTask().runWithRetry()
        assertContains(result, "[попыток: 5")
        assertContains(result, "Бэкап создан")
    }

    @Test
    fun checkBackupDescribe(){
        val result = BackUpTask().describe()
        assertEquals("Задача: Бэкап", result)
    }

    @Test
    fun checkSmokeTestMeasurable() {
        val result = SmokeTest().report()
        assertContains(result,"заняло 1500 мс")
    }

    @Test
    fun checkSmokeTestRetryable(){
        val result = SmokeTest().runWithRetry()
        assertContains(result, "[попыток: 2")
        assertContains(result, "smoke пройден")
    }
}

