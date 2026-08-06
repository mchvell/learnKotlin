package moduleThree

import kotlin.test.assertEquals
import kotlin.test.Test
import kotlin.test.assertFailsWith

class UserFactoryTest {

    @Test
    fun createUserWithName() {
        val user = UserFactory().create("Антон")
        assertEquals("Антон", user.name)
    }

    @Test
    fun createTwoUsers() {
        val result = UserFactory()
        result.create("Валя")
        result.create("Галя")

        val expected = listOf("Валя", "Галя")

        assertEquals(expected, result.createdNames)
    }

}