package woowacourse.payments.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardHolderNameTest {
    @Test
    fun `올바른 이름은 생성에 성공한다`() {
        val cardHolderName = CardHolderName("TAMA SEO")
        assertEquals("TAMA SEO", cardHolderName.cardHolderName)
    }

    @Test
    fun `이름이 30자를 초과하면 예외가 발생한다`() {
        val longName = "A".repeat(31)

        val exception =
            assertThrows<IllegalArgumentException> {
                CardHolderName(longName)
            }
        assertEquals("카드 소지자 이름은 30자 이하여야 합니다.", exception.message)
    }

    @Test
    fun `이름에 알파벳과 공백 외의 문자가 포함되면 예외가 발생한다`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                CardHolderName("서타마")
            }
        assertEquals("카드 소지자 이름은 대문자 알파벳으로만 구성되어야 합니다.", exception.message)
    }

    @Test
    fun `이름에 숫자가 포함되면 예외가 발생한다`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                CardHolderName("TAMA SEO11")
            }
        assertEquals("카드 소지자 이름은 대문자 알파벳으로만 구성되어야 합니다.", exception.message)
    }
}
