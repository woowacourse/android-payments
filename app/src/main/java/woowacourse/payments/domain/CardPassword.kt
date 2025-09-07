package woowacourse.payments.domain

@JvmInline
value class CardPassword(val password: String) {
    val isValid: Boolean
        get() = password.length == PASSWORD_LENGTH

    companion object {
        private const val PASSWORD_LENGTH = 4
    }
}