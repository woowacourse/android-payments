package woowacourse.payments.domain.model

import woowacourse.payments.domain.parser.ExpirationDateParser
import woowacourse.payments.domain.validator.ValidationErrorType
import java.time.Clock
import java.time.YearMonth

@JvmInline
value class ExpirationDate private constructor(
    val value: YearMonth,
) {
    companion object {
        const val EXPIRATION_DATE_LENGTH = 4

        private fun now(clock: Clock) = YearMonth.now(clock)

        fun from(
            value: YearMonth,
            clock: Clock = Clock.systemDefaultZone(),
        ): ExpirationDate {
            require(!value.isBefore(now(clock)))
            return ExpirationDate(value)
        }

        fun validate(
            raw: String,
            clock: Clock = Clock.systemDefaultZone(),
        ): ValidationErrorType? {
            if (raw.count(Char::isDigit) < EXPIRATION_DATE_LENGTH) return null
            val ym = ExpirationDateParser.parse(raw) ?: return ValidationErrorType.InvalidFormat
            return if (ym.isBefore(now(clock))) ValidationErrorType.ExpiredDate else null
        }
    }
}
