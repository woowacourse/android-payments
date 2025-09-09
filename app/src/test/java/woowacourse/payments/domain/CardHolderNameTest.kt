package woowacourse.payments.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.intArrayOf

class CardHolderNameTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 10, 20, 30])
    fun `카드 소유자 이름은 30자 이하이다`(value: Int) {
        // given
        val name = "A".repeat(value)

        // when & then
        shouldNotThrowAny {
            CardHolderName(name)
        }
    }

    @Test
    fun `카드 소유자 이름이 30자를 초과하면 예외가 발생한다`() {
        // given
        val name = "A".repeat(31)

        // when & then
        shouldThrow<IllegalArgumentException> {
            CardHolderName(name)
        }
    }
}
