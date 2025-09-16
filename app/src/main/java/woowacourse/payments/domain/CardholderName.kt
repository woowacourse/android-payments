package woowacourse.payments.domain

@JvmInline
value class CardholderName private constructor(
    val value: String,
) {
    sealed class CardholderNameException(
        message: String,
    ) : Exception(message) {
        data object LengthExceededException :
            CardholderNameException("소유자 이름은 최대 ${MAX_LENGTH}자까지 입력할 수 있습니다.") {
            private fun readResolve(): Any = LengthExceededException
        }

        data object InvalidFormatException :
            CardholderNameException("소유자 이름은 영문과 공백만 입력할 수 있습니다.") {
            private fun readResolve(): Any = InvalidFormatException
        }
    }

    companion object {
        const val MAX_LENGTH = 30

        fun from(value: String): CardholderName {
            if (!value.all { it.isLetter() || it.isWhitespace() }) throw CardholderNameException.InvalidFormatException
            if (value.length > MAX_LENGTH) throw CardholderNameException.LengthExceededException

            return CardholderName(value)
        }
    }
}
