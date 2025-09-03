package woowacourse.payments.domain

@JvmInline
value class Password private constructor(
    val value: String,
) {
    val isValid: Boolean
        get() = value.length == 4

    companion object {
        fun create(input: String): Password? {
            val digits = input.filter { it.isDigit() }
            return if (digits.length == 4) Password(digits) else null
        }
    }
}
