package woowacourse.payments.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardNumberTest {
    @Test
    fun `올바른 카드 번호는 생성에 성공해야 한다`() {
        val cardNumber = CardNumber("1234567890123456")
        assertEquals("1234567890123456", cardNumber.number)
    }

    @Test
    fun `카드 번호가 16자리가 아니면 예외가 발생한다`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                CardNumber("1234")
            }
        assertEquals("카드 번호는 16자리 숫자여야 합니다.", exception.message)
    }

    @Test
    fun `카드 번호에 숫자가 아닌 문자가 포함되면 예외가 발생한다`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                CardNumber("12345678901234AB")
            }
        assertEquals("카드 번호는 16자리 숫자여야 합니다.", exception.message)
    }
}
