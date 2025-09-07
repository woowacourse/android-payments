package woowacourse.payments.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class CardNumberTest {
    @Test
    fun `onValueChange는 카드 번호 문자열을 4자리씩 나눈다`() {
        // given
        val number = "1234567890123456"
        val cardNumber = CardNumber("", "", "", "")

        // when
        val actual = cardNumber.onValueChange(number)

        // then
        assertEquals("1234", actual.firstNumber)
        assertEquals("5678", actual.secondNumber)
        assertEquals("9012", actual.thirdNumber)
        assertEquals("3456", actual.fourthNumber)
    }

    @Test
    fun `toString은 각 부분의 카드 번호를 하나의 문자열로 합친다`() {
        // given
        val cardNumber = CardNumber("1234", "5678", "9012", "3456")

        // when
        val actual = cardNumber.toString()

        // then
        assertEquals("1234567890123456", actual)
    }
}
