package moduleThree

/*
1. Смерть заглушки (ядро — рефакторинг своего кода)
Возьми свой PriceModifierNew и сделай базу абстрактной:
- abstract class, fun apply — abstract, БЕЗ тела
- describe() и init с require — остаются готовыми (полуфабрикат!)
- Markup/Discount — не должны измениться ВООБЩЕ (проверь это утверждение)
+ добавь третий модификатор: FixedFeeModifier(fee: Int) : "Комиссия",
apply прибавляет фиксированную сумму (require fee >= 0)
*/

abstract class PriceModifierNew(val name: String){
    init {
        require(name.isNotBlank()) {"Название модификатора не может быть пустым, $name"}
    }
    abstract fun apply(price: Int): Int

    fun describe(): String = "Модификатор: $name"
}

class MarkupModifierNew(val percent: Int): PriceModifierNew("Наценка"){
    init {
        require(percent in 0..100){"Наценка должна быть в диапозоне 0-100, $percent"}
    }
    override fun apply(price: Int) = price + price * percent / 100
}

class DiscountModifier(val percent: Int): PriceModifierNew("Скидка"){
    init {
        require(percent in 0..100){"Скидка должна быть в диапозоне 0-100, $percent"}
    }
    override fun apply(price: Int) = price - price * percent / 100
}

class FixedFeeModifier(val fee: Int): PriceModifierNew("Комиссия"){
    init {
        require(fee >= 0){"Комиссия не может быть отрицательной, $fee"}
    }
    override fun apply(price: Int) = price + fee
}

/*
abstract class ReportSection:
  - abstract val title: String          ← свойство-ДЫРКА: без значения вообще
  - abstract fun body(): String
  - fun render(): String = "== $title ==\n${body()}"   ← готовый, использует ОБЕ дырки

Наследники: SummarySection (title = "Итоги", body — своя строка со смыслом)
и FailuresSection (title = "Падения", body — своя)

*/

abstract class ReportSection{
    abstract val title: String
    abstract fun body(): String
    fun render(): String = "== $title ==\n${body()}"
}

class SummarySection: ReportSection(){
    override val title = "Итоги"
    override fun body(): String = "Страница 3:: [$title]"
}

class FailuresSection: ReportSection(){
    override val title = "Падения"
    override fun body(): String = "Страница 4: [$title]"
}


/*
3-new. NotificationSender — с нуля, сразу ПРАВИЛЬНО спроектировать
Контекст: система шлёт уведомления разными каналами. «Отправитель вообще» —
бессмыслица (куда шлёт?), но у всех отправителей общая механика.

abstract class NotificationSender(val recipient: String):
  - abstract val channelName: String            ← дырка-свойство
  - abstract fun deliver(message: String): String   ← дырка-метод
  - fun send(message: String): String — ГОТОВЫЙ (Template Method!):
      require(message.isNotBlank()) { ... }
      возвращает "[$channelName -> $recipient] ${deliver(message)}"

Наследники (2 шт):
  EmailSender: channelName "Email", deliver возвращает "доставлено: '$message'"
  SmsSender: channelName "SMS", deliver обрезает message до 10 символов
    (загугли или вспомни: у String есть .take(n)) и возвращает
    "доставлено: '<обрезанное>...'"
*/

abstract class NotificationSender(val recipient: String){
//    init {
//        require(recipient.isNotBlank()){"Получатель должен существовать, $recipient"}
//    }
    abstract val channelName: String
    abstract fun deliver(message: String): String

    fun send(message: String): String{
        require(message.isNotBlank()){"Ошибка сервиса, сообщение не может быть пустым, $message"}
        return "[$channelName -> $recipient] ${deliver(message)}"
    }
}

class EmailSender(recipient: String): NotificationSender(recipient){
    override val channelName = "Email"
    override fun deliver(message: String): String = "доставлено: '$message'"
}

class SmsSender(recipient: String): NotificationSender(recipient){
    override val channelName = "SMS"
    override fun deliver(message: String): String {
        if (message.length > 10){
            return "доставлено: '${message.take(10)}...'"
        }
        return "доставлено: '$message'"
    }
}