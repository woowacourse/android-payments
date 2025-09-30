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

        fun create(
            value: YearMonth,
            clock: Clock = Clock.systemDefaultZone(),
        ): ExpirationDate {
            val current = now(clock)
            require(!value.isBefore(current)) { "만료일은 현재보다 과거일 수 없습니다." }
            return ExpirationDate(value)
        }

        fun createFromRaw(
            raw: String,
            clock: Clock = Clock.systemDefaultZone(),
        ): ExpirationDate {
            val yearMonth =
                ExpirationDateParser.parseOrNull(raw)
                    ?: throw IllegalArgumentException("만료일 형식이 올바르지 않습니다.")
            return create(yearMonth, clock)
        }

        fun validationErrorType(
            raw: String,
            clock: Clock = Clock.systemDefaultZone(),
        ): ValidationErrorType? {
            if (raw.isBlank()) return ValidationErrorType.InvalidFormat
            if (raw.count(Char::isDigit) < EXPIRATION_DATE_LENGTH) return ValidationErrorType.InvalidFormat

            val yearMonth =
                ExpirationDateParser.parseOrNull(raw) ?: return ValidationErrorType.InvalidFormat
            val current = now(clock)
            if (yearMonth.isBefore(current)) return ValidationErrorType.ExpiredDate

            return null
        }
    }
}
