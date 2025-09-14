package woowacourse.payments.domain

@JvmInline
value class Passcode(
    val value: String,
) {
    init {
        require(value.all(Char::isDigit)) { NON_NUMERIC_CHARACTER_ERROR_MESSAGE }
        require(value.length == PASSCODE_REQUIRED_LENGTH) { REQUIRED_LENGTH_ERROR_MESSAGE }
    }

    companion object {
        const val PASSCODE_REQUIRED_LENGTH = 4
        private const val NON_NUMERIC_CHARACTER_ERROR_MESSAGE = "비밀번호는 숫자로만 이루어질 수 있습니다."
        private const val REQUIRED_LENGTH_ERROR_MESSAGE =
            "비밀번호는 ${PASSCODE_REQUIRED_LENGTH}자여야 합니다."
    }
}
