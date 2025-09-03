package woowacourse.payments.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardholderNameTest {
    @Test
    fun `카드 소유자 이름이 30자를 초과하면 오류가 발생한다`() {
        // given
        val cardholderNameValue = "0".repeat(31)

        // when & then
        assertThrows<IllegalArgumentException> { CardholderName(cardholderNameValue) }
    }
}
