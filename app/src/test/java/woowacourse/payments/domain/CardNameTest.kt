package woowacourse.payments.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class CardNameTest {
    @Test
    fun `영어로만 CardName를 생성할 수 있다`() {
        // given
        val input = "park jiwon"

        // when
        // then
        assertDoesNotThrow { CardName(input) }
    }

    @Test
    fun `숫자로는 CardName를 생성할 수 없다`() {
        // given
        val input = "1234"

        // when
        // then
        assertThrows<IllegalArgumentException> { CardName(input) }
    }

    @Test
    fun `한글로는 CardName를 생성할 수 없다`() {
        // given
        val input = "한글"

        // when
        // then
        assertThrows<IllegalArgumentException> { CardName(input) }
    }
}
