package woowacourse.payments.domain

data class Card(
    val cardNumber: CardNumber,
    val expiredDate: ExpiredDate,
    val ownerName: OwnerName,
    val password: Password,
    val cardCompany: CardCompany,
) {
    companion object {
        fun from(
            cardNumber: String,
            expiredDate: String,
            ownerName: String,
            password: String,
            cardCompany: CardCompany,
        ): Result<Card> =
            runCatching {
                val cardNumber = CardNumber(cardNumber)
                val expiredDate = ExpiredDate.of(expiredDate) ?: throw IllegalArgumentException()
                val ownerName = OwnerName(ownerName)
                val password = Password(password)

                Card(cardNumber, expiredDate, ownerName, password, cardCompany)
            }
    }
}
