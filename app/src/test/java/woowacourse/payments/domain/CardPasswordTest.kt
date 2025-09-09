package woowacourse.payments.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardPasswordTest {
    @Test
    fun `비밀번호는 4자리이다`() {
        // given
        val password = "1234"

        // when & then
        shouldNotThrowAny {
            CardPassword(password)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "0", "123", "12345"])
    fun `비밀번호가 4자리가 아니면 예외가 발생한다`(value: String) {
        // when & then
        shouldThrow<IllegalArgumentException> {
            CardPassword(value)
        }
    }

    @Test
    fun `비밀번호에 숫자가 아닌 문자가 포함되면 예외가 발생한다`() {
        // given
        val password = "123a"

        // when & then
        shouldThrow<IllegalArgumentException> {
            CardPassword(password)
        }
    }
}
