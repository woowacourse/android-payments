package woowacourse.payments.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PasswordTest {
    @Test
    fun `올바른 비밀번호는 생성에 성공한다`() {
        val password = Password("1234")
        assertEquals("1234", password.password)
    }

    @Test
    fun `비밀번호가 4자리가 아니면 예외가 발생한다`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                Password("123")
            }
        assertEquals("비밀번호는 4자리 숫자여야 합니다.", exception.message)
    }

    @Test
    fun `비밀번호에 숫자가 아닌 문자가 포함되면 예외가 발생한다`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                Password("12a4")
            }
        assertEquals("비밀번호는 4자리 숫자여야 합니다.", exception.message)
    }
}
