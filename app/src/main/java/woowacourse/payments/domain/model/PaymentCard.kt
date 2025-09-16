package woowacourse.payments.domain.model

data class PaymentCard(
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
                    cardNumber = CardNumber.require(cardNumber),
                    expiry = Expiry.require(expiry),
                    owner = owner,
                    pin = Pin.require(pin),
                    bank = bank,
                )
            }
    }
}
