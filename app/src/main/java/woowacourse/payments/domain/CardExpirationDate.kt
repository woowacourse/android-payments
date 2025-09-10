package woowacourse.payments.domain

import androidx.core.text.isDigitsOnly
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CardExpirationDate private constructor(
    val yearMonth: YearMonth,
) {
    fun isExpired(now: YearMonth = YearMonth.now()): Boolean = yearMonth < now

    sealed class CardExpirationDateError(
        message: String,
    ) : Exception(message) {
        class ExceedsLength : CardExpirationDateError("만료일은 ${VALID_CARD_EXPIRATION_DATE_LENGTH}자리여야 합니다. (입력값이 너무 깁니다)")

        class InsufficientLength : CardExpirationDateError("만료일은 ${VALID_CARD_EXPIRATION_DATE_LENGTH}자리여야 합니다. (입력값이 너무 짧습니다)")

        class NonDigit : CardExpirationDateError("만료일은 숫자만 입력할 수 있습니다.")

        class UnsupportedDate : CardExpirationDateError("존재하지 않거나 유효하지 않은 만료일입니다.")
    }

    companion object {
        private const val VALID_CARD_EXPIRATION_DATE_LENGTH = 4
        private val formatter = DateTimeFormatter.ofPattern("MMyy")

        fun from(value: String): CardExpirationDate {
            if (!value.isDigitsOnly()) throw CardExpirationDateError.NonDigit()
            if (value.length < VALID_CARD_EXPIRATION_DATE_LENGTH) throw CardExpirationDateError.InsufficientLength()
            if (value.length > VALID_CARD_EXPIRATION_DATE_LENGTH) throw CardExpirationDateError.ExceedsLength()

            return runCatching { YearMonth.parse(value, formatter) }
                .mapCatching { yearMonth -> CardExpirationDate(yearMonth) }
                .getOrElse { throw CardExpirationDateError.UnsupportedDate() }
        }
    }
}
