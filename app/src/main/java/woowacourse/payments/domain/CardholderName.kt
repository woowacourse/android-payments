package woowacourse.payments.domain

@JvmInline
value class CardholderName private constructor(
    val value: String,
) {
    sealed class CardholderNameError(
        message: String,
    ) : Exception(message) {
        class ExceedsLength : CardholderNameError("소유자 이름은 최대 ${MAX_LENGTH}자까지 입력할 수 있습니다.")

        class InvalidFormat : CardholderNameError("소유자 이름은 영문과 공백만 입력할 수 있습니다.")
    }

    companion object {
        const val MAX_LENGTH = 30

        fun from(value: String): CardholderName {
            if (!value.all { it.isLetter() || it.isWhitespace() }) throw CardholderNameError.InvalidFormat()
            if (value.length > MAX_LENGTH) throw CardholderNameError.ExceedsLength()

            return CardholderName(value)
        }
    }
}
