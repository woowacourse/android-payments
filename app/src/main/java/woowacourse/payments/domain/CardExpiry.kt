package woowacourse.payments.domain

import java.time.YearMonth

@JvmInline
value class CardExpiry(val value: YearMonth) {
    init {
        require(value.isAfter(MINIMUM_VALID_EXPIRY) || value == MINIMUM_VALID_EXPIRY) { "카드 만료일은 $MINIMUM_VALID_EXPIRY 이후여야 합니다." }
    }

    companion object {
        private val MINIMUM_VALID_EXPIRY = YearMonth.now()
    }
}
