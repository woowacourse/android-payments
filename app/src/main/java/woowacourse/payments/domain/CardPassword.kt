package woowacourse.payments.domain

class CardPassword(
    val password: List<Int>,
) {
    init {
        require(password.size == CARD_PASSWORD_LENGTH) { ERROR_INVALID_CARD_PASSWORD_LENGTH }
    }

    companion object {
        const val CARD_PASSWORD_LENGTH = 4
        private const val ERROR_INVALID_CARD_PASSWORD_LENGTH = "카드 비밀번호는 4자리여야 합니다."
    }
}
