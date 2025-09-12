package woowacourse.payments.domain.exception

class InvalidPinException : IllegalArgumentException(ERROR_INVALID_PIN) {
    companion object {
        private const val ERROR_INVALID_PIN = "유효하지 않은 비밀번호입니다."
    }
}
