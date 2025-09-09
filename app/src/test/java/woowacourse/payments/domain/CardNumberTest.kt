package woowacourse.payments.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardNumberTest {
    @Test
    fun `카드 번호는 16자리이다`() {
        // given
        val input = "0".repeat(16)

        // when
        val cardNumber = CardNumber.from(input)

        // then
        cardNumber.numbers shouldHaveSize 16
        cardNumber.numbers.joinToString("") { it.value.toString() } shouldBe input
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "0123456789", "012345678901234", "01234567890123456"])
    fun `카드 번호가 16자리가 아니면 예외가 발생한다`(value: String) {
        // when & then
        shouldThrow<IllegalArgumentException> {
            CardNumber.from(value)
        }
    }

    @Test
    fun `숫자가 아닌 문자는 무시된다`() {
        // given
        val input = "0123-4567-8901-2345"

        // when
        val cardNumber = CardNumber.from(input)

        // then
        cardNumber.numbers.size shouldBe 16
        cardNumber.numbers.joinToString("") { it.value.toString() } shouldBe "0123456789012345"
    }
}
