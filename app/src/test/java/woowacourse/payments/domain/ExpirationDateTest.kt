package woowacourse.payments.domain

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.YearMonth

class ExpirationDateTest {
    @ParameterizedTest
    @ValueSource(strings = ["2024-07", "2025-05"])
    fun `만료일이 현재 연월보다 먼저면 오류가 발생한다`(value: String) {
        // given
        val expirationYearMonth = YearMonth.parse(value)
        val currentYearMonth = YearMonth.parse("2025-06")

        // when & then
        assertThrows<IllegalArgumentException> {
            ExpirationDate(expirationYearMonth, currentYearMonth)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["2025-06", "2025-07"])
    fun `만료일이 현재 연월과 같거나 나중이면 과거면 오류가 발생하지 않는다`(value: String) {
        // given
        val expirationYearMonth = YearMonth.parse(value)
        val currentYearMonth = YearMonth.parse("2025-06")

        // when & then
        assertDoesNotThrow {
            ExpirationDate(expirationYearMonth, currentYearMonth)
        }
    }
}
