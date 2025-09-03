package woowacourse.payments.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PasscodeTest {
    @ParameterizedTest
    @ValueSource(strings = ["abcd", "a123"])
    fun `비밀번호에 숫자가 아닌 문자가 있으면 오류가 발생한다`(value: String) {
        assertThrows<IllegalArgumentException> { Passcode(value) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "12345"])
    fun `비밀번가 4자가 아니면 오류가 발생한다`(value: String) {
        assertThrows<IllegalArgumentException> { Passcode(value) }
    }

    @Test
    fun `비밀번호가 숫자 4자로 이루어졌으면 오류가 발생하지 않는다`() {
        // given
        val passcodeValue = "1234"

        // when & then
        assertDoesNotThrow { Passcode(passcodeValue) }
    }
}
