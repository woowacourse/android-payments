package woowacourse.payments.domain

data class CardExpirationDate(
    val month: Int,
    val year: Int,
) {
    init {
        require(month in MIN_EXPIRATION_MONTH..MAX_EXPIRATION_MONTH) { ERROR_INVALID_EXPIRATION_MONTH }
    }

    companion object {
        private const val MIN_EXPIRATION_MONTH = 1
        private const val MAX_EXPIRATION_MONTH = 12
        private const val ERROR_INVALID_EXPIRATION_MONTH = "유효하지 않은 월입니다."
    }
}
