package woowacourse.payments

sealed class InputType {
    data object Password : InputType()

    data object CardNumber : InputType()

    data object ExpiryDate : InputType()

    fun format(raw: String): String =
        when (this) {
            is CardNumber -> {
                val digits = raw.filter { it.isDigit() }.take(CARD_NUMBER_MAX_LENGTH)
                digits.chunked(CARD_NUMBER_CHUNK_SIZE).joinToString(CARD_NUMBER_SEPARATOR)
            }

            is ExpiryDate -> {
                val digits = raw.filter { it.isDigit() }.take(EXPIRY_MAX_LENGTH)
                if (digits.length <= EXPIRY_CHUNK_SIZE) {
                    digits
                } else {
                    digits.chunked(EXPIRY_CHUNK_SIZE).joinToString(EXPIRY_SEPARATOR)
                }
            }

            is Password -> {
                raw.filter { it.isDigit() }.take(PASSWORD_MAX_LENGTH)
            }
        }

    companion object {
        private const val CARD_NUMBER_MAX_LENGTH = 16
        private const val CARD_NUMBER_CHUNK_SIZE = 4
        private const val CARD_NUMBER_SEPARATOR = " - "

        private const val EXPIRY_MAX_LENGTH = 4
        private const val EXPIRY_CHUNK_SIZE = 2
        private const val EXPIRY_SEPARATOR = " / "

        private const val PASSWORD_MAX_LENGTH = 4
    }
}
