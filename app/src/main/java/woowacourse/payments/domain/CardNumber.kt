package woowacourse.payments.domain

@JvmInline
value class CardNumber(
    val value: String,
) {
    val isValid: Boolean
        get() = value.length == NUMBER_LENGTH

    companion object {
        private const val NUMBER_LENGTH = 16
    }
}
