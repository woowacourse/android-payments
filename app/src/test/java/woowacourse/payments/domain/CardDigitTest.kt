package woowacourse.payments.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardDigitTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 1, 5, 9])
    fun `카드 번호의 각 자리는 0에서 9까지의 숫자이다`(value: Int) {
        // when & then
        shouldNotThrowAny {
            CardDigit(value)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [-5, -1, 10, 100])
    fun `카드 번호의 각 자리가 0에서 9까지의 숫자가 아니면 예외가 발생한다`(value: Int) {
        // when & then
        shouldThrow<IllegalArgumentException> {
            CardDigit(value)
        }
    }
}
