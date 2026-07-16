package moduleThree

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue



class NotificationSenderTest {
    /*
    Тесты (NotificationSenderTest.kt) — сам спроектируй набор. Минимум который жду:
      - оба require работают (два отдельных негативных теста — правило «одна бомба»)
      - полиморфный список сендеров → у всех send непустой
     */
    @Test
    fun checkSendEmail(){
        val result = EmailSender("v@gmail.com").send("Полет навигатора на луну")
        assertContains(result, "Email")
        assertContains(result, "Полет")
        assertContains(result, "v@gmail.com")
    }

    @Test
    fun checkSendSms(){
        val result = SmsSender("70009992211").send("Привет, пойдем сегодня пить пиво?")
        assertContains(result, "SMS")
        assertContains(result, "...")
        assertContains(result, "70009992211")
    }

    @Test
    fun checkShortSmsStrip(){
        val result = SmsSender("007").send("Тапки")
        assertFalse{"..." in result}
    }

    @Test
    fun checkLongSmsStrip(){
        val result = SmsSender("009").send("Рефрижератор сломался")
        assertContains(result, "Рефрижерат")
        assertFalse(result.contains("сломался"))
        assertTrue(result.contains("..."))
    }

    @Test
    fun checkFailSendEmail(){
        assertFailsWith<IllegalArgumentException> {EmailSender("o@gmail.com").send("   ")}
    }

    @Test
    fun checkFailSendSms(){
        assertFailsWith<IllegalArgumentException> { SmsSender("  ").send("  ")}
    }

    @Test
    fun checkSendChain(){
        val senders = listOf(EmailSender("c@gmail.com"),
            SmsSender("222222222"))
        for (sender in senders) {
            assertTrue(sender.send("Проверка связи").isNotBlank(),
                "Пустой send у ${sender.channelName}")
        }
    }
}