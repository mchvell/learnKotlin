package moduleThree

/*
Интерфейс Retryable — способность «повторяемая операция»:
  - val maxAttempts: Int                    ← контракт-свойство
  - fun execute(): String                   ← контракт-метод
  - fun runWithRetry(): String — DEFAULT-метод (с телом):
      возвращает "[попыток: $maxAttempts] ${execute()}"

Два класса ИЗ РАЗНЫХ МИРОВ (без общего родителя!):
  ApiHealthCheck : Retryable — maxAttempts 3, execute "ping OK"
  DbMigration : Retryable — maxAttempts 1, execute "миграция применена"
*/

interface Retryable{
    val maxAttempts: Int
    fun execute(): String
    fun runWithRetry(): String = "[попыток: $maxAttempts] ${execute()}"
}

class ApiHealthCheck: Retryable{
    override val maxAttempts = 3
    override fun execute(): String = "ping OK"
}

class DbMigration: Retryable{
    override val maxAttempts = 1
    override fun execute(): String = "миграция применена"
}

/*
Класс из ДВУХ миров сразу: наследование + интерфейс вместе.
Возьми свой Retryable как есть. Добавь:

abstract class Task(val taskName: String):
  - init: require на taskName (not blank)
  - fun describe(): String = "Задача: $taskName"

class BackupTask : И наследник Task("Бэкап"), И подписант Retryable:
  - maxAttempts 5, execute "бэкап создан"

В main ничего не пиши — сразу вопрос ДО тестов:
у BackupTask есть и describe() (от класса), и runWithRetry() (от интерфейса).
Предскажи: оба работают? Что вернёт каждый?
*/

abstract class Task(val taskName: String){
    init {
        require(taskName.isNotBlank()) {"название задачи не должно быть пустым, $taskName"}
    }

    fun describe(): String = "Задача: $taskName"
}

class BackUpTask: Task("Бэкап"), Retryable{
    override val maxAttempts = 5
    override fun execute(): String = "Бэкап создан"
}

/*
Интерфейс Measurable:
  - fun durationMs(): Long                  ← контракт
  - fun report(): String — default: "заняло ${durationMs()} мс"

Твой Retryable — как есть, не трогай.

class SmokeTest — подписант ОБОИХ (Measurable и Retryable):
  - maxAttempts 2, execute "smoke пройден"
  - durationMs возвращает 1500

Вопрос до тестов: у SmokeTest теперь два default-метода из разных
контрактов (runWithRetry и report). Конфликта нет — почему?
(Раздел 5 конспекта говорил, КОГДА конфликт есть, — примени наоборот.)
*/

interface Measurable{
    fun durationMs(): Long
    fun report(): String = "заняло ${durationMs()} мс"
}

class SmokeTest: Retryable, Measurable{
    override val maxAttempts = 2
    override fun execute(): String = "smoke пройден"
    override fun durationMs(): Long = 1500
}
