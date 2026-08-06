package moduleThree

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class CreateUserTest {

    @Test
    fun regularUserIsBuiltWithApply() {
        val result = TestUser().apply {
            name = "Max"
            email = "i1@yandex.ru"
        }
        assertEquals("Max", result.name)
        assertEquals("i1@yandex.ru", result.email)
        assertFalse(result.isAdmin)
    }

    @Test
    fun adminCreatedCorrectly() {
        val result = TestUser().apply {
            name = "Matt"
            isAdmin = true
        }
        assertEquals("Matt", result.name)
        assertTrue(result.isAdmin)
    }

    @Test
    fun applyReturnsTheObjectItself() {
        assertEquals("Grisha", TestUser().apply {name = "Grisha"}.name)
    }
}