package woowacourse.payments.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class CardPasswordTest {
    @Test
    fun `숫자 4자리로만 카드 비밀번호를 생성할 수 있다`() {
        // given
        val password = "1".repeat(4)

        // when
        // then
        assertDoesNotThrow { CardPassword(password) }
    }

    @Test
    fun `숫자 4자리가 아니면 카드 비밀번호를 생성할 수 없다`() {
        // given
        val tooShort = "1".repeat(3)
        val tooLong = "1".repeat(5)

        // when
        // then
        assertAll(
            { assertThrows<IllegalArgumentException> { CardPassword(tooShort) } },
            { assertThrows<IllegalArgumentException> { CardPassword(tooLong) } },
        )
    }

    @Test
    fun `문자로 카드 비밀번호를 생성할 수 없다`() {
        // given
        val value = "abcd"

        // when
        // then
        assertThrows<IllegalArgumentException> { CardPassword(value) }
    }
}