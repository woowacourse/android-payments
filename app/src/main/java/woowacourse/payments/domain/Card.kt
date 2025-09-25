package woowacourse.payments.domain

data class Card(
    val id: Long,
    val cardNumber: CardNumber,
    val expirationDate: ExpirationDate,
    val cardHolderName: CardHolderName,
    val password: Password,
    val bank: Bank,
) {
    companion object {
        private var currentId = 0L

        fun newCard(
            id: Long? = null,
            number: String,
            expirationDate: String,
            cardHolderName: String,
            password: String,
            bank: Bank,
        ): Result<Card> =
            runCatching {
                Card(
                    id = id ?: generateId(),
                    cardNumber = CardNumber(number),
                    expirationDate = ExpirationDate(expirationDate),
                    cardHolderName = CardHolderName(cardHolderName),
                    password = Password(password),
                    bank = bank,
                )
            }

        private fun generateId(): Long = currentId++
    }
}
