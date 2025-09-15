package woowacourse.payments.domain.model

import java.time.YearMonth

@JvmInline
value class ExpirationDate private constructor(
    val value: YearMonth,
) {
    init {
        require(value >= YearMonth.now()) { "만료일은 현재 연월보다 이후여야 합니다." }
    }

    companion object {
        fun from(value: YearMonth): ExpirationDate = ExpirationDate(value)
    }
}
