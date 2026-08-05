package moduleThree

interface FeatureFlags {
    fun isEnabled(key: String): Boolean
    fun variant(key: String) : String?
}

class Banner(private val featureFlag: FeatureFlags) {
    fun shouldShow(): Boolean = featureFlag.isEnabled("banner")
    fun text(): String = featureFlag.variant("banner")?.let { "Баннер: $it" } ?: "Баннер по умолчанию"
}

class ApiSession(val baseUrl: String) {
    var loadCount: Int = 0
        private set

    val token: String by lazy {
        loadCount++
        "token-for-$baseUrl"
    }

}


/*
fun main() {
    val x = object : FeatureFlags {
        override fun isEnabled(key: String): Boolean = true
        override fun variant(key: String): String? = null
    }

    val y = object : FeatureFlags {
        override fun isEnabled(key: String): Boolean = false
        override fun variant(key: String): String = "new-year"
    }

    val firstBannerText = Banner(x).text()
    println(firstBannerText)

    val firstBannerShouldShown = Banner(x).shouldShow()
    println(firstBannerShouldShown)

    val secondBannerText = Banner(y).text()
    println(secondBannerText)

    val secondBannerShouldShown = Banner(y).shouldShow()
    println(secondBannerShouldShown)

    val session = ApiSession("https://my-json-server.typicode.com")
    println(session.loadCount)

    session.token
    println(session.loadCount)

    session.token
    println(session.loadCount)
}
*/