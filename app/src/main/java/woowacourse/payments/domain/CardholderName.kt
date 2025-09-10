package woowacourse.payments.domain

data class CardholderName(
    val value: String,
) {
    init {
        require(value.length <= CARDHOLDER_NAME_MAX_LENGTH) { IllegalArgumentException() }
    }

    companion object {
        const val CARDHOLDER_NAME_MAX_LENGTH = 30
    }
}
