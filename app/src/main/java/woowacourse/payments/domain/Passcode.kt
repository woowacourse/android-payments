package woowacourse.payments.domain

data class Passcode(
    val value: String,
) {
    init {
        require(value.all(Char::isDigit)) { IllegalArgumentException() }
        require(value.length == PASSCODE_REQUIRED_LENGTH) { IllegalArgumentException() }
    }

    companion object {
        const val PASSCODE_REQUIRED_LENGTH = 4
    }
}
