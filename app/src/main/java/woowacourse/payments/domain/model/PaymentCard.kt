package woowacourse.payments.domain.model

import java.util.UUID

data class PaymentCard(
    val id: String,
    val cardNumber: CardNumber,
    val expiry: Expiry,
    val owner: String,
    val pin: Pin,
    val bank: BankType,
) {
    companion object {
        fun create(
            cardNumber: String,
            expiry: String,
            owner: String,
            pin: String,
            bank: BankType,
        ): Result<PaymentCard> =
            runCatching {
                PaymentCard(
                    id = UUID.randomUUID().toString(),
                    cardNumber = CardNumber.require(cardNumber),
                    expiry = Expiry.require(expiry),
                    owner = owner,
                    pin = Pin.require(pin),
                    bank = bank,
                )
            }
    }
}
