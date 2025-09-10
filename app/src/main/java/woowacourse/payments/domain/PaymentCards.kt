package woowacourse.payments.domain

@JvmInline
value class PaymentCards(private val _cards: Set<PaymentCard>) {
    fun add(card: PaymentCard): PaymentCards = PaymentCards(_cards + card)

    fun isContain(card: PaymentCard): Boolean = _cards.contains(card)

    val cards get() = _cards.toList()
}
