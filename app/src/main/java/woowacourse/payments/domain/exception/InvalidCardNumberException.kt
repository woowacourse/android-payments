package woowacourse.payments.domain.exception

class InvalidCardNumberException : IllegalArgumentException(ERROR_INVALID_CARD_NUMBER) {
    companion object {
        private const val ERROR_INVALID_CARD_NUMBER = "유효하지 않은 카드번호입니다."
    }
}
