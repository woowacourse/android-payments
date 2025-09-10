package woowacourse.payments.ui.formatter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.payments.domain.ExpirationDate
import java.time.YearMonth

class ExpirationDateFormatTest {
    @Test
    fun `만료일은 4자리씩 나누어 표시되고, 마지막 8자는 마스킹된다`() {
        // given
        val expirationDate = ExpirationDate(YearMonth.of(2099, 12))

        // when
        val actual: String = ExpirationDateFormat.formatted(expirationDate)

        // then
        val expected = "12 / 99"
        assertThat(actual).isEqualTo(expected)
    }
}
