package woowacourse.payments.ui.formatter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.payments.domain.CardNumber

class CardNumberFormatTest {
    @Test
    fun `카드 번호는 4자리씩 나누어 표시되고, 마지막 8자는 마스킹된다`() {
        // given
        val cardNumber = CardNumber("1234123412341234")

        // when
        val actual: String = CardNumberFormat.formattedCardNumber(cardNumber)

        // then
        val expected = "1234 - 1234 - **** - ****"
        assertThat(actual).isEqualTo(expected)
    }
}
