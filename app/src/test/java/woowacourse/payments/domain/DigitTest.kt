package woowacourse.payments.domain

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class DigitTest {
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
    @ParameterizedTest
    fun `Digit은 0~9 사이의 숫자로만 생성할 수 있다`(value: Int) {
        // given
        val number = value

        // when
        // then
        assertDoesNotThrow { Digit(number) }
    }

    @ValueSource(ints = [-1, 10])
    @ParameterizedTest
    fun `Digit은 0~9 범위 외의 숫자면 아니면 에러를 발생시킨다`(value: Int) {
        // given
        val number = value

        // when
        // then
        assertThrows<IllegalArgumentException> { Digit(number) }
    }
}