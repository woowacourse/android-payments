package woowacourse.payments.domain

@JvmInline
value class Password(
    val value: String,
) {
    val isValid: Boolean
        get() = value.length == 4
}
