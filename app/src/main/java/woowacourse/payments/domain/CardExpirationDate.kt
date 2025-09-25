package woowacourse.payments.domain

import java.time.YearMonth

class CardExpirationDate private constructor(
    val month: Int,
    val year: Int,
) {
    companion object {
        private const val YEAR_OFFSET = 2_000
        private const val INVALID_INPUT = Int.MIN_VALUE
        const val REQUIRE_CARD_EXPIRATION_DATE_LENGTH = 4

        fun from(value: String): CardExpirationDateStatus {
            if (value.length != REQUIRE_CARD_EXPIRATION_DATE_LENGTH) {
                return CardExpirationDateStatus.Error(CardExpirationErrorCode.INVALID_LENGTH)
            }

            val month: Int = value.take(2).toIntOrNull() ?: INVALID_INPUT
            val year: Int = value.takeLast(2).toIntOrNull() ?: INVALID_INPUT
            return validateExpirationDate(month = month, year = year)
        }

        private fun validateExpirationDate(
            month: Int,
            year: Int,
            now: YearMonth = YearMonth.now(),
        ): CardExpirationDateStatus {
            val yearMonth =
                runCatching { YearMonth.of(YEAR_OFFSET + year, month) }.getOrNull()
                    ?: return CardExpirationDateStatus.Error(CardExpirationErrorCode.INVALID_FORMAT)
            if (yearMonth.isBefore(now)) {
                return CardExpirationDateStatus.Error(CardExpirationErrorCode.PAST_DATE)
            }
            return CardExpirationDateStatus.Success(CardExpirationDate(month, year))
        }
    }
}
