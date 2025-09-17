package woowacourse.payments.domain

data class Card(
    val number: CardNumber,
    val expirationDate: ExpirationDate,
    val cardHolderName: CardHolderName,
    val password: Password,
    val bank: Bank,
) {
    companion object {
        fun newCard(
            number: String,
            expirationDate: String,
            cardHolderName: String,
            password: String,
            bank: Bank,
        ): Result<Card> =
            runCatching {
                Card(
                    number = CardNumber(number),
                    expirationDate = ExpirationDate(expirationDate),
                    cardHolderName = CardHolderName(cardHolderName),
                    password = Password(password),
                    bank = bank,
                )
            }
    }
}
