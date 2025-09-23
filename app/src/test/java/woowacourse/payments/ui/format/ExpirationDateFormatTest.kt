package woowacourse.payments.ui.format

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.payments.domain.ExpirationDate
import java.time.YearMonth

class ExpirationDateFormatTest {
    @Test
    fun `만료일은 월 2자와 연 2자로 나누어 표시된다`() {
        // given
        val expirationDate = ExpirationDate(YearMonth.of(2099, 12))

        // when
        val actual: String =
            ExpirationDateFormat.formatted(
                expirationDate.value.format(
                    ExpirationDateFormat.formatPattern,
                ),
            )

        // then
        val expected = "12 / 99"
        assertThat(actual).isEqualTo(expected)
    }
}
