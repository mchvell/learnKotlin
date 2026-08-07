package mockServer

enum class HttpMethod(val hasBody: Boolean) {
    GET(false),
    POST(true),
    PUT(true),
    DELETE(false);

    fun isRead(): Boolean = this == GET

    companion object {
        val DEFAULT = GET

        fun parseOrNull(raw: String?): HttpMethod? = when (raw?.trim()?.uppercase()) {
            "GET" -> GET
            "POST" -> POST
            "PUT" -> PUT
            "DELETE" -> DELETE
            else -> null
        }
    }
}