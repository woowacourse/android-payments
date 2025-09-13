package woowacourse.payments.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class CardNumberTest {
    @Test
    fun `16자리 숫자로만 카드 번호를 생성할 수 있다`() {
        // given
        val cardNumber = "1".repeat(16)

        // when
        // then
        assertDoesNotThrow { CardNumber(cardNumber) }
    }

    @Test
    fun `16자리가 아니면 카드 번호를 생성할 수 없다`() {
        // given
        val tooShort = "1".repeat(15)
        val tooLong = "1".repeat(17)

        // when
        // then
        assertAll(
            { assertThrows<IllegalArgumentException> { CardNumber(tooShort) } },
            { assertThrows<IllegalArgumentException> { CardNumber(tooLong) } },
        )
    }

    @Test
    fun `문자로 카드 번호를 생성할 수 없다`() {
        // given
        val value = "abcd".repeat(4)

        // when
        // then
        assertThrows<IllegalArgumentException> { CardPassword(value) }
    }
}