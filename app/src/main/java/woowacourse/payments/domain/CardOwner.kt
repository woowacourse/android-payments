package woowacourse.payments.domain

@JvmInline
value class CardOwner(
    val value: String,
) {
    val isValid: Boolean
        get() = value.isEmpty() || (value.length <= MAX_LENGTH && value.all { it.isLetter() || it.isWhitespace() })

    companion object {
        private const val MAX_LENGTH = 30

        fun create(input: String): CardOwner = CardOwner(input.trim())
    }
}
