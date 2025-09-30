package woowacourse.payments.domain

import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardPasswordTest {
    @Test
    fun `카드 비밀번호는 숫자로 이뤄져 있어야 합니다`() {
        assertThrows<IllegalArgumentException> { CardPassword("ABCD") }
            .shouldHaveMessage("카드 비밀번호는 숫자로만 이루어져 있어야 합니다. (입력값: ABCD)")
    }

    @ValueSource(ints = [3, 5])
    @ParameterizedTest
    fun `카드 비밀번호는 4자리여야 합니다`(length: Int) {
        assertThrows<IllegalArgumentException> { CardPassword("1".repeat(length)) }
            .shouldHaveMessage("카드 비밀번호는 4자리여야 합니다. (입력된 길이: $length)")
    }
}
