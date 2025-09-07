package woowacourse.payments.domain

@JvmInline
value class CardNumber(val number: String) {
    val isValid: Boolean
        get() = number.length == NUMBER_LENGTH

    companion object {
        private const val NUMBER_LENGTH = 16
    }
}