package moduleFour.scheduler

class Scheduler(val testCases: List<TestCase>) {

    fun distribute(buckets: Int): List<Bucket> {
        require(buckets > 0) {"Кол-во корзин не может быть 0 или меньше, $buckets" }
        val bucketList = List(buckets) { Bucket(it) }
        val sortedByDuration = testCases.sortedByDescending { it.durationMs }

        for (testCase in sortedByDuration) {
            bucketList.minByOrNull { it.totalMs }?.addTestCase(testCase)
        }
        return bucketList
    }

    fun byTag(tag: String): List<TestCase> {
        return testCases.filter { it.tags.contains(tag) }
    }

    fun longerThan(ms: Int): List<TestCase> {
        return testCases.filter { it.durationMs > ms }
    }

    val totalMs: Int
        get() = testCases.sumOf { it.durationMs }

    val slowest: TestCase?
        get() = testCases.maxByOrNull { it.durationMs }

}