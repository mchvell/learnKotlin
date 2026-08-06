package moduleThree

import moduleTwo.add
import moduleTwo.result

class TestUser(var name: String = "Misha", var email: String = "m@yahoo.com", var isAdmin: Boolean = false)

class UserFactory() {
    val createdNames = mutableListOf<String>()

    fun create(name: String): TestUser {
        val result = TestUser()
            .apply { this.name = name }
            .also { createdNames.add(name) }
        return result
    }
}