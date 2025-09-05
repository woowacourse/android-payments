package woowacourse.payments.domain

@JvmInline
value class Expired(
    val value: String,
) {
    val isValid: Boolean
        get() = value.length == 4 && value.take(2).toIntOrNull() in 1..12
}
