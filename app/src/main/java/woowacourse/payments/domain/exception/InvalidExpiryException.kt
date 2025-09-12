package woowacourse.payments.domain.exception

class InvalidExpiryException : IllegalArgumentException(ERROR_INVALID_EXPIRY) {
    companion object {
        private const val ERROR_INVALID_EXPIRY = "유효하지 않은 만료일입니다."
    }
}
