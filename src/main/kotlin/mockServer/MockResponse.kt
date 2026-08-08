package mockServer

data class MockResponse(val code: Int, val body: String = "") {
    init {
        require(code in 100..599) {"Код должен быть в диапозоне от 100 до 599, $code"}
    }

    val isSuccess: Boolean
        get() = code in 200..299

    companion object {
        val NOT_FOUND = MockResponse(404, "not found")

        fun ok(body: String): MockResponse {
            return MockResponse(200, body)
        }
    }
}