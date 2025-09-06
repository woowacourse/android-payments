package woowacourse.payments.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class CardNumberTest {
    @Test
    fun `카드_번호는_문자가_포함되지_않는다`() {
        // given
        val numbers = "abcd1234test5678"

        // when & then
        shouldThrow<IllegalArgumentException> {
            CardNumber(numbers)
        }
    }

    @Test
    fun `카드_번호는_16자리_이하여야_한다`() {
        // given
        val numbers = "11112222333344445555"

        // when & then
        shouldThrow<IllegalArgumentException> {
            CardNumber(numbers)
        }
    }
}
