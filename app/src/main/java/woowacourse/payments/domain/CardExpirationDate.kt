package woowacourse.payments.domain

import woowacourse.payments.domain.extension.isDigitsOnly
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@JvmInline
value class CardExpirationDate private constructor(
    val value: YearMonth,
) {
    fun isExpired(now: YearMonth = YearMonth.now()): Boolean = value < now

    sealed class CardExpirationDateException(
        message: String,
    ) : Exception(message) {
        class InvalidLengthException(
            val kind: Kind,
        ) : CardExpirationDateException("만료일은 ${VALID_CARD_EXPIRATION_DATE_LENGTH}자리여야 합니다.") {
            enum class Kind { INSUFFICIENT, EXCEEDS }
        }

        data object NonDigitException : CardExpirationDateException("만료일은 숫자만 입력할 수 있습니다.") {
            private fun readResolve(): Any = NonDigitException
        }

        data object UnsupportedDateException :
            CardExpirationDateException("존재하지 않거나 유효하지 않은 만료일입니다.") {
            private fun readResolve(): Any = UnsupportedDateException
        }
    }

    companion object {
        private const val VALID_CARD_EXPIRATION_DATE_LENGTH = 4
        private val formatter = DateTimeFormatter.ofPattern("MMyy")

        fun from(value: String): CardExpirationDate {
            if (!value.isDigitsOnly()) throw CardExpirationDateException.NonDigitException
            if (value.length < VALID_CARD_EXPIRATION_DATE_LENGTH) {
                throw CardExpirationDateException.InvalidLengthException(
                    CardExpirationDateException.InvalidLengthException.Kind.INSUFFICIENT,
                )
            }
            if (value.length > VALID_CARD_EXPIRATION_DATE_LENGTH) {
                throw CardExpirationDateException.InvalidLengthException(CardExpirationDateException.InvalidLengthException.Kind.EXCEEDS)
            }

            return runCatching { YearMonth.parse(value, formatter) }
                .mapCatching { yearMonth -> CardExpirationDate(yearMonth) }
                .getOrElse { throw CardExpirationDateException.UnsupportedDateException }
        }
    }
}
