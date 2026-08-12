package moduleFour.scheduler


data class TestCase(val name: String, val suite: String,
    val durationMs: Int, val tags: List<String> = emptyList()) {
    init {
        require(name.isNotBlank()) { "название не должно быть пустым, $name" }
        require(durationMs >= 0) {"длительность не может быть отрицательной, $durationMs ms"}
    }
}



