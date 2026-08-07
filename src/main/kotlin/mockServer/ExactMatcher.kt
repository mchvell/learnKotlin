package mockServer

class ExactMatcher(val expected: String): PathMatcher {
    init {
        require(expected.isNotBlank()) { "ожидаемое не должно быть пустым $expected" }
    }

    override fun matches(path: String): Boolean = path == expected

}