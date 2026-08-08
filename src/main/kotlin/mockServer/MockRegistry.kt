package mockServer

class MockRegistry {
    private val rules = mutableListOf<MockRule>()

    fun addRule(rule: MockRule) {
        rules.add(rule)
    }

    val rulesCount: Int
        get() = rules.size

    fun findResponse(request: MockRequest): MockResponse? {
        for (rule in rules) {
            if (rule.matches(request)) {
                return rule.response
            }
        }
        return null
    }

    fun respondTo(request: MockRequest): MockResponse = findResponse(request) ?: MockResponse.NOT_FOUND

    fun reset() = rules.clear()
}

