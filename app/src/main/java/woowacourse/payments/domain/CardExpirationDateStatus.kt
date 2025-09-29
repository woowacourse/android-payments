package woowacourse.payments.domain

sealed class CardExpirationDateStatus {
    data class Success(
        val cardExpirationDate: CardExpirationDate,
    ) : CardExpirationDateStatus()

    data class Error(
        val errorCode: CardExpirationErrorCode,
    ) : CardExpirationDateStatus()
}
