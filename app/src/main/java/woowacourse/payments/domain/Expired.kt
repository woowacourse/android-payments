package woowacourse.payments.domain

@JvmInline
value class Expired(
    val value: String,
) {
    val isValid: Boolean
        get() {
            if (value.length != 4) {
                return false
            }

            val mm = value.substring(0, 2)

            return mm.matches(Regex("(0[1-9]|1[0-2])"))
        }
}
