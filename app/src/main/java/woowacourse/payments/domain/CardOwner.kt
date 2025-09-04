package woowacourse.payments.domain

@JvmInline
value class CardOwner(
    val value: String,
) {
    val isValid: Boolean
        get() = value.isEmpty() || value.all { it.isLetter() || it.isWhitespace() }
}
