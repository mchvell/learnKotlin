package moduleFour.scheduler

class Bucket(val index: Int) {
    private val testCases = mutableListOf<TestCase>()

    fun addTestCase(testCase: TestCase) {
        testCases.add(testCase)
    }

    val totalMs: Int
        get() = testCases.sumOf { it.durationMs }

    val caseNames: List<String>
        get() = testCases.map { it.name }

    val size: Int
        get() = testCases.size
}