package mockServer

class PrefixMatcher(val prefix: String): PathMatcher {
    init {
        require(prefix.isNotBlank()) {"префикс не должен быть пустым $prefix"}
    }
    override fun matches(path: String): Boolean = path.startsWith(prefix)
}