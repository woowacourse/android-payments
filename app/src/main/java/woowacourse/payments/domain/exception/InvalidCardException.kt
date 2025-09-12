package woowacourse.payments.domain.exception

sealed class InvalidCardException(
    message: String,
) : IllegalArgumentException(message) {
    class InvalidCardNumber : InvalidCardException(ERROR_INVALID_CARD_NUMBER)

    class InvalidExpiry : InvalidCardException(ERROR_INVALID_EXPIRY)

    class InvalidPin : InvalidCardException(ERROR_INVALID_PIN)

    companion object {
        const val ERROR_INVALID_CARD_NUMBER = "유효하지 않은 카드번호입니다."
        const val ERROR_INVALID_EXPIRY = "유효하지 않은 만료일입니다."
        const val ERROR_INVALID_PIN = "유효하지 않은 비밀번호입니다."
    }
}
