package woowacourse.payments.domain

@JvmInline
value class CardNumber private constructor(
    val value: String,
) {
    val isValid: Boolean
        get() = value.length == 16

    companion object {
        fun create(input: String): CardNumber? {
            val digits = input.filter { it.isDigit() }.take(16)
            return CardNumber(digits)
        }
    }
}
