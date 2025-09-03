package woowacourse.payments.domain

@JvmInline
value class Expired private constructor(
    val value: String,
) {
    val isValid: Boolean
        get() {
            val (mm, yy) = value.split("/")
            return mm.matches(Regex("(0[1-9]|1[0-2])")) && yy.length == 2
        }

    companion object {
        fun create(input: String): Expired? {
            val digits = input.filter { it.isDigit() }.take(4)
            if (digits.length < 4) return null

            val mm = digits.take(2)
            val yy = digits.takeLast(2)
            val formatted = "$mm/$yy"

            return Expired(formatted)
        }
    }
}
