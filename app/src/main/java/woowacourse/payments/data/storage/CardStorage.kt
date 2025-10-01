package woowacourse.payments.data.storage

import woowacourse.payments.data.model.request.CardRequest
import woowacourse.payments.data.model.request.EditCardRequest
import woowacourse.payments.data.model.request.NewCardRequest
import woowacourse.payments.data.model.response.CardResponse
import java.util.concurrent.atomic.AtomicLong

object CardStorage {
    private val idGenerator: AtomicLong = AtomicLong(1L)
    private val storage: LinkedHashMap<Long, CardResponse> = LinkedHashMap()

    fun saveCard(cardRequest: CardRequest): Result<Long> =
        runCatching {
            when (cardRequest) {
                is NewCardRequest -> addCard(cardRequest)
                is EditCardRequest -> updateCard(cardRequest)
            }
        }

    private fun addCard(cardRequest: CardRequest): Long {
        val newCardId = idGenerator.getAndIncrement()
        val savedCard =
            CardResponse(
                id = newCardId,
                numberDigits = cardRequest.numberDigits,
                expiry = cardRequest.expiry,
                holder = cardRequest.holder,
                bankType = cardRequest.bankType,
            )
        storage[newCardId] = savedCard
        return newCardId
    }

    private fun updateCard(cardRequest: EditCardRequest): Long {
        val existingCard = storage[cardRequest.id]
        return if (existingCard != null) {
            val updatedCard =
                existingCard.copy(
                    numberDigits = cardRequest.numberDigits,
                    expiry = cardRequest.expiry,
                    holder = cardRequest.holder,
                    bankType = cardRequest.bankType,
                )
            storage[cardRequest.id] = updatedCard
            cardRequest.id
        } else {
            addCard(cardRequest)
        }
    }
}
