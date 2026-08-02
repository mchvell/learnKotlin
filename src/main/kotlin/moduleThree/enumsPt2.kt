package moduleThree

enum class TestStatus(val symbol: String, val isProblem: Boolean) {
    PASSED("✓", false),
    FAILED("✗", true),
    SKIPPED("-", false),
    BLOCKED("⊘", true);

    fun needsRerun(): Boolean = this == FAILED
}

fun line(name: String, status: TestStatus): String = "${status.symbol} $name"

fun verdict(status: TestStatus): String = when (status) {
    TestStatus.PASSED -> "Успешно пройден"
    TestStatus.FAILED -> "Зафейлен, перепройти"
    TestStatus.SKIPPED -> "Пропущен, определить причину"
    TestStatus.BLOCKED -> "Тест заблокирован, разобрать"
}

enum class Environment(val baseUrl: String,
                       val timeoutSeconds: Int,
                       val allowsDestructiveTests: Boolean) {
    DEV("https://dev.api.local", 5, true),
    STAGE("https://stage.api.local", 10, true),
    PROD("https://api.wb.ru", 15, false);

    fun isProduction(): Boolean = this == PROD
}

fun endpoint(env: Environment, path: String): String = env.baseUrl + path

fun guard(env: Environment): String = when (env) {
    Environment.DEV -> "Среда: Dev, возможны ошибки"
    Environment.STAGE -> "Среда: Stage, возможны новые версии методов"
    Environment.PROD -> "Среда: Prod, возможна работа анти-бота"
}

fun parse(raw: String?): Environment? = when (raw?.trim()?.uppercase()) {
    "DEV" -> Environment.DEV
    "STAGE" -> Environment.STAGE
    "PROD" -> Environment.PROD
    else -> null
}

enum class HttpStatusCategory(val label: String, val isError: Boolean) {
    INFORMATIONAL("Информационный", false),
    SUCCESS("Успех", false),
    REDIRECT("Перенаправление", false),
    CLIENT_ERROR("Ошибка клиента", true),
    SERVER_ERROR("Ошибка сервера", true);

    fun isRetryable(): Boolean = this == SERVER_ERROR
}

fun categoryOf(code: Int): HttpStatusCategory? = when (code) {
    in 100..199 -> HttpStatusCategory.INFORMATIONAL
    in 200..299 -> HttpStatusCategory.SUCCESS
    in 300..399 -> HttpStatusCategory.REDIRECT
    in 400..499 -> HttpStatusCategory.CLIENT_ERROR
    in 500..599 -> HttpStatusCategory.SERVER_ERROR
    else -> null
}

fun describe(code: Int): String = "$code — ${categoryOf(code)?.label ?: "неизвестный код"}"

fun shouldRetry(code: Int): Boolean = categoryOf(code)?.isRetryable() ?: false

enum class DiscountType(val label: String, val needsValue: Boolean) {
    NONE("Без скидки", false) {
        override fun apply(priceKopecks: Int, value: Int): Int = priceKopecks
    },
    PERCENT("Процентная", true) {
        override fun apply(priceKopecks: Int, value: Int): Int = priceKopecks - priceKopecks * value / 100
    },
    FIXED("Фиксированная", true) {
        override fun apply(priceKopecks: Int, value: Int): Int = if(priceKopecks < value) 0 else priceKopecks - value
    },
    HALF("Половина цены", false) {
        override fun apply(priceKopecks: Int, value: Int): Int = priceKopecks / 2
    };

    abstract fun apply(priceKopecks: Int, value: Int): Int
}

fun finalPrice(type: DiscountType, priceKopecks: Int, value: Int): Int = type.apply(priceKopecks, value)

fun describeDiscount(type: DiscountType, value: Int) : String = if(type.needsValue) "${type.label} ($value)" else type.label

fun parseType(raw: String?) : DiscountType? = when (raw?.trim()?.uppercase()) {
    "NONE" -> DiscountType.NONE
    "PERCENT" -> DiscountType.PERCENT
    "FIXED" -> DiscountType.FIXED
    "HALF" -> DiscountType.HALF
    else -> null
}