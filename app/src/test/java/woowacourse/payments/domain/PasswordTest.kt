package woowacourse.payments.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class PasswordTest {
    @Test
    fun `비밀번호는_4글자여야_한다`() {
        // given
        val password = "12345"

        // when & then
        shouldThrow<IllegalArgumentException> {
            Password(password)
        }
    }

    @Test
    fun `비밀번호는_문자가_포함되지_않는다`() {
        // given
        val password = "일이삼사"

        // when & then
        shouldThrow<IllegalArgumentException> {
            Password(password)
        }
    }
}
