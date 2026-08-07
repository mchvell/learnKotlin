package mockServer

class MockRule(val matcher: PathMatcher, val method: HttpMethod, val response: MockResponse) {

    fun matches(request: MockRequest): Boolean = request.method == method && matcher.matches(request.path)

    companion object {
        fun get(path: String, response: MockResponse): MockRule = MockRule(
            ExactMatcher(path),
            HttpMethod.GET,
            response
        )

        fun post(path: String, response: MockResponse): MockRule = MockRule(
            ExactMatcher(path),
            HttpMethod.POST,
            response
        )
    }
}