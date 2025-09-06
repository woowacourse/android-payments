package woowacourse.payments.domain

@JvmInline
value class CardNumber(
    val value: String,
) {
    val isValid: Boolean
        get() = value.length == 16
}
