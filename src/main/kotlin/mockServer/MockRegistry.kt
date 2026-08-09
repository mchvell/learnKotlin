package mockServer

class MockRegistry {
    private val rules = mutableListOf<MockRule>()

    fun addRule(rule: MockRule) {
        rules.add(rule)
    }

    val rulesCount: Int
        get() = rules.size

    fun findResponse(request: MockRequest): MockResponse? = rules.find { it.matches(request) }?.response


    fun respondTo(request: MockRequest): MockResponse = findResponse(request) ?: MockResponse.NOT_FOUND

    fun reset() = rules.clear()
}

