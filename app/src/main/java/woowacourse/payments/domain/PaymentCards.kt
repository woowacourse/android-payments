package woowacourse.payments.domain

import java.util.concurrent.atomic.AtomicLong

object PaymentCards {
    private val lastId: AtomicLong = AtomicLong()
    private val _cards: MutableMap<Long, PaymentCard> = mutableMapOf()
    val cards: List<PaymentCard> = _cards.values.toList()

    fun registerOrUpdate(
        id: Long? = null,
        bankType: BankType,
        number: CardNumber,
        expirationDate: CardExpirationDate,
        cardholderName: CardholderName,
        password: CardPassword,
    ): Result<PaymentCard> =
        runCatching {
            PaymentCard(
                id = id ?: lastId.incrementAndGet(),
                bankType = bankType,
                number = number,
                expirationDate = expirationDate,
                cardholderName = cardholderName,
                password = password,
            )
        }.onSuccess { newCard -> _cards[newCard.id] = newCard }
}
