package moduleThree

import kotlin.reflect.typeOf

class RetryPolicy(val attempts: Int, val delayMs: Int) {
    companion object {
        const val MAX_ATTEMPTS = 5
        const val DEFAULT_DELAY_MS = 1000
        val DEFAULT = RetryPolicy(3, DEFAULT_DELAY_MS)
    }

    init {
        require(attempts in 1..MAX_ATTEMPTS) {"Кол-во попыток строго от 1 до $MAX_ATTEMPTS, $attempts"}
        require(delayMs >= 0) {"Таймаут не может быть отрицательным, $delayMs мс"}
    }


    fun totalWaitMs(): Int = (attempts - 1) * delayMs
}

class TestCase(val name: String, val suite: String) {

    companion object {
        const val MAX_NAME_LENGTH = 60

        var createdCount: Int = 0
            private set

        fun reset() {
            createdCount = 0
        }
    }

    init {
        require(name.isNotBlank()) { "Навзание кейса не может быть пустым, $name" }
        require(name.length <= MAX_NAME_LENGTH) {"Длинна имени строго не выше, $MAX_NAME_LENGTH, текущая ${name.length}"}
        require(suite.isNotBlank()) {"Набор не должен быть пусты, $suite"}
        createdCount ++
    }

    fun fullName(): String = "$suite.$name"

}

class Timeout(val millis: Int) {
    companion object {
        const val MAX_MILLIS = 60_000
        val SHORT = Timeout(1000)
        val MEDIUM = Timeout(5000)
        val LONG = Timeout(30_000)
    }

    init {
        require(millis in 1..MAX_MILLIS) { "мс должны быть в диапозоне от 1 мс до $MAX_MILLIS мс, $millis" }
    }

    val seconds: Int
        get() = millis / 1000

    fun isLong(): Boolean = millis > 10_000
}

class Version(val major: Int, val minor: Int, val patch: Int) {
    companion object {
        fun fromString(raw: String): Version {
            val parts = raw.split('.')
            require(parts.size == 3) {"ожидался формат major.minor.patch, по факту $raw"}
            val f = parts[0].trim().toIntOrNull()
            val s = parts[1].trim().toIntOrNull()
            val t = parts[2].trim().toIntOrNull()
            require(f != null && s != null && t != null) {"первое - $f, второе – $s, третье – $t не должны быть null"}
            return Version(f, s, t)
        }
        fun parseOrNull(raw: String?) : Version? {
            val parts = raw?.split('.')
            if (parts == null || parts.size != 3) return null


            val f = parts[0].trim().toIntOrNull()
            val s = parts[1].trim().toIntOrNull()
            val t = parts[2].trim().toIntOrNull()

            if (f == null || s == null || t == null) return null
            if (f < 0 || s < 0 || t < 0) return null

            return Version(f, s, t)
        }
    }
    init {
        require(major >= 0) {"Значение не может быть отрицательным, $major"}
        require(minor >= 0) {"Значение не может быть отрицательным, $minor"}
        require(patch >= 0) {"Значение не может быть отрицательным, $patch"}
    }

    val text
        get() = "$major.$minor.$patch"

    fun isNewerThan(other: Version): Boolean {
        if (major != other.major) return major > other.major
        if (minor != other.minor) return minor > other.minor
        return patch > other.patch
    }
}

class MoneyN(val kopecks: Int) {
    init {
        require(kopecks >= 0) {"Сумма не может быть отрицательной, $kopecks"}
    }

    companion object {
        fun fromKopecks(value: Int): MoneyN = MoneyN(value)

        fun fromRubles(value: Int): MoneyN = MoneyN(value * 100)
    }

    val rubles: Int
        get() = kopecks / 100

}


class Temperature private constructor(val celsius: Double) {

    companion object {
        const val ABSOLUTE_ZERO = -273.15

        fun fromCelsius(value: Double): Temperature = Temperature(value)

        fun fromFahrenheit(value: Double): Temperature = Temperature((value - 32) * 5 / 9)
    }

    init {
        require(celsius >= ABSOLUTE_ZERO) {"Температура не может быть меньше абсолютного нуля, $celsius"}
    }

    val fahrenheit: Double
        get() = celsius * 9 / 5 + 32

    val isFreezing: Boolean
        get() = celsius <= 0
}


class RatingNew private constructor(val stars: Int) {
    companion object {
        fun of(stars: Int): RatingNew = RatingNew(stars)

        fun fromVotes(totalStars: Int, votesCount: Int): RatingNew {
            require(votesCount > 0) {"Количество оценок должно быть строго > 0, $votesCount"}
            val avgRating = totalStars / votesCount
            return RatingNew(avgRating)
        }
    }

    init {
        require(stars in 1..5){"Рейтинг от 1 до 5, $stars"}
    }
}

enum class LogLevel(val label: String, val weight: Int) {
    TRACE("Трассировка", 10),
    DEBUG("Отладка", 20),
    INFO("Информация", 30),
    WARN("Предупреждение", 40),
    ERROR("Ошибка", 50);

    fun isAtLeast(other: LogLevel): Boolean = weight >= other.weight

    companion object {
        val DEFAULT = INFO

        fun parseOrNull(raw: String?) = when (raw?.trim()?.uppercase()) {
            "TRACE" -> TRACE
            "DEBUG" -> DEBUG
            "INFO" -> INFO
            "WARN" -> WARN
            "ERROR" -> ERROR
            else -> null
        }
    }

}

fun main() {
    val x = LogLevel.DEBUG.isAtLeast(LogLevel.WARN)
    println(x)
}