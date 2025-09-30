package woowacourse

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import woowacourse.payments.ui.toYearMonth
import woowacourse.payments.ui.toYearMonthString
import java.time.YearMonth

class YearMonthFormatTest {
    @ParameterizedTest
    @CsvSource(
        "1234, 2034, 12",
        "1235, 2035, 12",
        "1236, 2036, 12",
    )
    fun `올바른 4자리 연도와 날짜를 입력하면 올바른 YearMonth를 반환한다`(
        input: String,
        expectedYear: Int,
        expectedMonth: Int,
    ) {
        // given

        // when

        // then
        val actual = YearMonth.of(expectedYear, expectedMonth)
        Assertions.assertEquals(input.toYearMonth(), actual)
    }

    @ParameterizedTest
    @ValueSource(strings = ["123", "뭉치바보", "2345"])
    fun `올바르지 않은 연도와 날짜를 입력하면 null를 반환한다`(value: String) {
        // given

        // when

        // then
        Assertions.assertNull(value.toYearMonth())
    }

    @Test
    fun `YearMonth를 올바른 문자열로 반환한다`() {
        // given
        val input = YearMonth.of(2025, 12)

        // when

        // then
        Assertions.assertEquals(input.toYearMonthString(), "12/25")
    }
}
