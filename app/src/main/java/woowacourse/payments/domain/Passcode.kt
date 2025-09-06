package woowacourse.payments.domain

data class Passcode(
    val value: String,
) {
    init {
        require(value.all(Char::isDigit)) { IllegalArgumentException() }
        require(value.length == PASSCODE_LENGTH) { IllegalArgumentException() }
    }

    companion object {
        private const val PASSCODE_LENGTH = 4
    }
}
