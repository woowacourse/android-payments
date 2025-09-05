package woowacourse.payments.domain

data class Password(
    val password: String = "",
) {
    override fun toString(): String {
        return password
    }

    fun onValueChange(value: String): Password {
        val newPassword = value.filter { it.isDigit() }.take(CARD_PASSWORD_MAX_LENGTH)
        return Password(newPassword)
    }

    companion object {
        const val CARD_PASSWORD_MAX_LENGTH = 4
    }
}
