package mockServer

val MockResponse.isServerError: Boolean
    get() = code in 500..599

fun MockRequest.summary(): String = "метод: ${this.method} путь: ${this.path}"

fun MockRegistry.addAll(rules: List<MockRule>) {
    for (rule in rules) {
        addRule(rule)
    }
}