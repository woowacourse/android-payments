package woowacourse.payments.domain

@JvmInline
value class CardPassword(val value: String) {
    val isValid: Boolean
        get() = value.length == PASSWORD_LENGTH

    companion object {
        private const val PASSWORD_LENGTH = 4
    }
}