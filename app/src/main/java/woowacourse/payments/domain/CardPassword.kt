package woowacourse.payments.domain

@JvmInline
value class CardPassword(
    val value: String,
) {
    init {
        require(value.length == CARD_PASSWORD_LENGTH) { ERROR_INVALID_CARD_PASSWORD_LENGTH }
        require(value.all(Char::isDigit)) { ERROR_INVALID_CARD_PASSWORD_FORMAT }
    }

    companion object {
        const val CARD_PASSWORD_LENGTH = 4
        private const val ERROR_INVALID_CARD_PASSWORD_LENGTH = "카드 비밀번호는 4자리여야 합니다."
        private const val ERROR_INVALID_CARD_PASSWORD_FORMAT = "카드 비밀번호는 숫자로만 이루어져야 합니다."
    }
}
