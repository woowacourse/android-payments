package woowacourse.payments.domain

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.ints.shouldNotBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardPasswordTest {
    @DisplayName("카드 비밀번호가 생성된다")
    @Test
    fun createCardPasswordTest() {
        // given
        val testPassword = "1234"

        // when
        val cardPassword = CardPassword(testPassword)

        // then
        assertSoftly(cardPassword) {
            value shouldBe testPassword
            isValid shouldBe true
        }
    }

    @DisplayName("카드 비밀번호가 4자리가 아닌 경우 false를 반환한다")
    @Test
    fun cardPasswordLengthTest() {
        // given
        val testPassword = "123"

        // when
        val cardPassword = CardPassword(testPassword)

        // then
        assertSoftly(cardPassword) {
            value.length shouldNotBeExactly 4
            isValid shouldBe false
        }
    }
}