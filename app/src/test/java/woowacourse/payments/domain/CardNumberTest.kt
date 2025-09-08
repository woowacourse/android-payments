package woowacourse.payments.domain

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.ints.shouldNotBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardNumberTest {
    @DisplayName("카드 번호가 생성된다")
    @Test
    fun createCardNumberTest() {
        // given
        val testNumber = "1234567812345678"

        // when
        val cardNumber = CardNumber(testNumber)

        // then
        assertSoftly(cardNumber) {
            value shouldBe testNumber
            isValid shouldBe true
        }
    }

    @DisplayName("카드 번호가 16자리가 아닌 경우 false를 반환한다")
    @Test
    fun cardNumberLengthTest() {
        // given
        val testNumber = "123456781234567"

        // when
        val cardNumber = CardNumber(testNumber)

        // then
        assertSoftly(cardNumber) {
            value.length shouldNotBeExactly 16
            isValid shouldBe false
        }
    }
}