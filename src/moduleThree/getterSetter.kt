package moduleThree

/*
1. Класс Playlist
Свойство name (var, String) — сеттер обрезает пробелы по краям (trim())
и не пускает blank-значения (require)
Свойство songsCount (var, Int) — сеттер не пускает отрицательные
Проверь: playlist.name = "  Rock  " → хранится "Rock"
playlist.name = "   " → исключение
*/

class Playlist(name: String, songsCount: Int) {
    var name = name
    set(value) {
        val trimmed = value.trim()
        require(trimmed.isNotBlank()) { "Имя не может быть пустым, получено: '$value'" }
        field = trimmed
    }

    var songsCount = songsCount
    set(value) {
        require(value >= 0) {"кол-во песен не может быть меньше нуля,$value"}
        field = value
    }
}

/*
2. Класс Circle
Свойство radius (var, Double), сеттер: require > 0
Вычисляемые свойства без backing field:
diameter (Double) — радиус * 2
area (Double) — 3.14159 * радиус в квадрате
Проверь: circle.radius = 5.0 → diameter 10.0; radius = -1.0 → исключение
*/

class Circle(radius: Double) {
    var radius: Double = radius
    set(value) {
        require(value > 0){"Радиус не может быть меньше или равен 0, $value"}
        field = value
    }
    init {
        require(radius > 0){"Радиус не может быть меньше или равен 0, $radius"}
    }
    val diameter: Double
        get() = radius * 2

    val area: Double
        get() = Math.PI * (radius * radius)
}

/*
3. Класс Account
Свойство balance (var, Int) — сеттер запрещает отрицательный баланс
Вычисляемое свойство isEmpty (Boolean) — balance == 0
+ init по вкусу (дыру рождения ты теперь видишь сам)
*/

class Account(balance: Int){
    var balance: Int = balance
    set(value) {
        require(value >= 0){"Баланс не может быть отрицательным, $value"}
        field = value
    }
    init {
        require(balance >= 0){"Баланс не может быть отрицательным, $balance"}
    }
    val isEmpty: Boolean
        get() = balance == 0
}

/*
4. TestSuite
totalTests (var, Int) — сеттер: require >= 0
failedTests (var, Int) — сеттер: require >= 0
passRate (Int) — вычисляемое свойство, формула из TestRun
init — по формуле защиты рождения
*/

class TestSuite(totalTests: Int, failedTests: Int) {
    var totalTests = totalTests
        set(value) {
            require(value >= 0){"Итоговое кол-во тестов не может отрицательным или нулевым, $value"}
            field = value
        }
    var failedTests = failedTests
        set(value) {
            require(value >= 0){"Кол-во сломанных тестов не может быть отрицательным или нулевым, $value"}
            field = value
        }
    init {
        require(totalTests >= 0){"Итоговое кол-во тестов не может отрицательным, $totalTests"}
        require(failedTests >= 0){"Кол-во сломанных тестов не может быть отрицательным, $failedTests"}
        require(failedTests <= totalTests){"Кол-во сломанных тестов не может быть больше общего кол-ва, $failedTests"}
    }

    val passRate: Int
        get() = 100 - failedTests * 100 / totalTests
}

/*
5. Класс Temperature v2 (возвращение!)
Свойство celsius (var, Double) — обычное хранимое
Свойство fahrenheit (var, Double) — БЕЗ backing field:
get() = celsius * 9 / 5 + 32
set(value) { celsius = (value - 32) * 5 / 9 }
Один источник правды — celsius; fahrenheit — два окна в него.
Проверь: t.fahrenheit = 212.0 → t.celsius == 100.0
Это решение той самой задачи с конфликтом сигнатур — без фабрик.
*/

class TemperatureV2(celsius: Double) {
    var celsius: Double = celsius

    var fahrenheit: Double
        get() = celsius * 9 / 5 + 32

        set(value) {
            celsius = (value - 32) * 5 / 9
        }

}

/*
6. Класс ApiEndpoint (разминка: сеттер + init, всё знакомое)
Свойство url (var, String) — сеттер: trim() + require на непустоту
Свойство timeoutMs (var, Int) — сеттер: require in 100..60000
(таймаут меньше 100мс — ошибка конфига, больше минуты — вечность)
Защити рождение. Контроль: ApiEndpoint("  ", 5000) → падает;
endpoint.timeoutMs = 99 → падает с сообщением про обе границы
*/

class ApiEndpoint(url: String, timeoutMs: Int) {
    var url: String = url
        set(value) {
            val trimmed = value.trim()
            require(trimmed.isNotBlank()){"УРЛ не может быть пустым, $value"}
            field = trimmed
        }
    var timeout: Int = timeoutMs
        set(value) {
            require(value in 100..60000){"Таймаут должен быть в пределах от 100ms до 60000ms, $value"}
            field = value
        }
    init {
        this.url = url
        this.timeout = timeoutMs
    }
}

/*
7. Класс TestReport (вычисляемые свойства, твой мир)
Хранимые: passed (var, Int), failed (var, Int), skipped (var, Int) —
все с require >= 0 в сеттерах + init
Вычисляемые (без единой ячейки!):
  total (Int) — сумма трёх
  hasFailures (Boolean) — failed > 0
  summary (String) — вида "42 passed, 3 failed, 1 skipped"
Вопрос после кода: почему summary НЕ должен быть хранимым var?
Ответ у тебя уже есть из Download — примени его к этому полю.
*/

class TestReport(passed: Int, failed: Int, skipped: Int) {
    var passed = passed
        set(value) {
            require(value >= 0){"Колличество пройденных тестов не может быть отрицательным, $value"}
            field = value
        }
    var failed = failed
        set(value) {
            require(value >= 0){"Колличество сломанных тестов не может быть отрицательным, $value"}
            field = value
        }
    var skipped = skipped
        set(value) {
            require(value >= 0){"Колличество пропущенных тестов не может быть отрицательным, $value"}
            field = value
        }
    init {
        this.passed = passed
        this.failed = failed
        this.skipped = skipped
    }
    val total: Int
        get() = passed + failed + skipped

    val summary: String
        get() = "$passed пройдено, $failed сломано, $skipped пропущено"

    val hasFailures: Boolean
        get() = failed > 0
}

/*
8. Класс FileSize (ЛИНЗА №1 — по образцу TemperatureV2, один в один)
Хранимое: bytes (var, Long) — сеттер: require >= 0
Линза: kilobytes (var, Double) — БЕЗ ячейки:
  get() — bytes / 1024.0 (заметь .0 — почему это важно?)
  set(value) — bytes = (value * 1024).toLong()
Контроль: f.kilobytes = 2.5 → f.bytes == 2560
Проследи цепочку записи вслух: set(kilobytes) → ... → где осело?
Это прямой повтор звёздочки — теперь руками.
*/

class FileSize(bytes: Long) {
    var bytes: Long = bytes
        set(value) {
            require(value > 0){"Количество байт не может быть 0 или отрицательным, $value"}
            field = value
        }
    var kilobytes: Double
        get() = bytes / 1024.0
        set(value) {
            bytes = (value * 1024).toLong()
        }
    init {
        this.bytes = bytes
    }
}

/*
9. Класс BugTracker (парный инвариант — реванш за TestSuite)
Хранимые: openBugs (var, Int), closedBugs (var, Int) — require >= 0
Вычисляемое: totalBugs (Int)
Метод closeBug() — переносит один баг из open в closed:
  require(openBugs > 0) { ... } внутри метода, потом два присваивания
Вопрос: closeBug() меняет ДВА поля — может ли внешний наблюдатель
застать объект между присваиваниями в «полусостоянии»?
(Ответ короткий, но подумай. Подсказка: мы в одном потоке.)
*/

class BugTracker(openBugs: Int, closeBugs: Int) {
    val errorText: String = "Значение не может быть отрицательным"
    var openBugs: Int = openBugs
        set(value) {
            require(value >= 0){"$errorText $value"}
            field = value
        }
    var closeBugs: Int = closeBugs
        set(value) {
            require(value >= 0){"$errorText $value"}
            field = value
        }
    init {
        this.openBugs = openBugs
        this.closeBugs = closeBugs
    }
    val totalBugs: Int
        get() = openBugs + closeBugs

    fun closedBugs() {
        require(openBugs > 0){"Чтобы закрыть баг, кол-во открытых должно быть > 0"}
        closeBugs += 1
        openBugs -= 1
    }

}

/*
10. Класс ProgressBar (ЛИНЗА №2 — чуть сложнее: линза с зажимом)
Хранимое: current (var, Int) — require in 0..max в сеттере
Хранимое: max (val, Int) — require > 0 в init (val — сеттера нет!)
Линза: percent (var, Int):
  get() — current * 100 / max
  set(value) — current = (value * max / 100).coerceIn(0, max)
Контроль: bar = ProgressBar(max = 200), bar.percent = 50 → current == 100
bar.percent = 150 → current == 200 (зажался), НЕ исключение
Вопрос: почему в set использован coerceIn, а не require? Когда что уместно?
*/

class ProgressBar(current: Int, val max: Int) {
    var current = current
        set(value) {
            require(value in 0..max){"Текущее значение должно быть от ноля и до максимального, $value"}
            field = value
        }

    init {
        this.current = current
        require(max > 0){"Максимальное значение должно быть больше ноля, $max"}
    }

    var percent: Int
        get() = current * 100 / max
        set(value) {
            current = (value * max / 100).coerceIn(0, max)
        }
}


fun main() {
    val playlist = Playlist("rock", 3)

    playlist.name = "  Rock  "
    println("'${playlist.name}'")     // что здесь — с пробелами или без?

    playlist.songsCount = 22
    println("${playlist.songsCount}")
    
    val c = Circle(5.0)
    println(c.diameter)
    println(c.area)
    c.radius = 2.0
    println(c.diameter)


    val suite = TestSuite(100, 10)
    suite.totalTests = 5
    println(suite.failedTests)
    println(suite.passRate)

    val api = ApiEndpoint("https://example.com/test", 150)
    api.timeout = 1000
    api.url = "https://example.com/test/new=flacky"
    println(api.url)
    println(api.timeout)

    val testRun = TestReport(100, 15,1)
    println(testRun.total)
    println(testRun.hasFailures)
    testRun.skipped = 10
    println(testRun.summary)

    val x = FileSize(1000)
    println(x.kilobytes)
    x.kilobytes = 2.6
    println(x.kilobytes)

    val bugs = BugTracker(10, 22)
    bugs.closedBugs()
    println(bugs.openBugs)
    println(bugs.closeBugs)
}