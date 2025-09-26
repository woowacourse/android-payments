package woowacourse.payments.data

import woowacourse.payments.domain.Card
import java.util.concurrent.atomic.AtomicLong

object CardStorage {
    private var insertId = AtomicLong(1)
    val cards = mutableMapOf<Long, Card>()

    fun addCard(card: Card) {
        val id = insertId.getAndIncrement()
        cards[id] = card
    }
}
