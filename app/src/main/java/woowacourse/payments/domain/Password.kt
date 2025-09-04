package woowacourse.payments.domain

data class Password(
    val password: String,
) {
    override fun toString(): String {
        return password
    }

    fun onValueChange(value: String): Password {
        val newPassword = value.filter { it.isDigit() }.take(4)
        return Password(newPassword)
    }
}
