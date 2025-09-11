package woowacourse.payments.domain

data class Card(
    val number: CardNumber,
    val expirationDate: CardExpirationDate,
    val password: CardPassword,
    val holderName: CardHolderName? = null,
) {
    init {
        require(!expirationDate.isExpired()) { ERROR_EXPIRED_CARD_EXPIRATION_DATE }
    }

    companion object {
        private const val ERROR_EXPIRED_CARD_EXPIRATION_DATE = "만료된 카드입니다."
    }
}
