package mockServer


data class MockRequest(val method: HttpMethod, val path: String, val body: String?) {
    init {
        require(path.isNotBlank()) { "путь не может быть пустым, $path" }
        require(path.startsWith("/")) {"путь должен начинаться с '/', $path" }
    }

    val hasBody: Boolean
        get() = !body.isNullOrEmpty()
}

