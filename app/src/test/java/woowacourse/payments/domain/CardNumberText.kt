package woowacourse.payments.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CardNumberText {
    @Test
    fun `카드 번호에 숫자가 아닌 문자가 있으면 오류가 발생한다`() {
        // given
        val cardNumberValue = "abcdabcdabcdabcd"

        // when & then
        assertThrows<IllegalArgumentException> { CardNumber(cardNumberValue) }
    }

    @Test
    fun `카드 번호가 16자가 아니면 오류가 발생한다`() {
        // given
        val cardNumberValue = "1234"

        // when & then
        assertThrows<IllegalArgumentException> { CardNumber(cardNumberValue) }
    }

    @Test
    fun `카드 번호가 숫자 16자로 이루어졌으면 오류가 발생하지 않는다`() {
        // given
        val cardNumberValue = "1234123412341234"

        // when & then
        assertDoesNotThrow { CardNumber(cardNumberValue) }
    }
}
