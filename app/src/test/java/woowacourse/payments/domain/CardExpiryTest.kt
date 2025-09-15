package woowacourse.payments.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.time.YearMonth

class CardExpiryTest {
    @Test
    fun `이번 달 이후는 유효한 카드 만료일을 생성할 수 있다`() {
        // given
        val thisMonth = YearMonth.now()
        val nextMonth = YearMonth.now().plusMonths(1L)

        // when
        // then
        assertAll(
            { assertDoesNotThrow { CardExpiry(thisMonth) } },
            { assertDoesNotThrow { CardExpiry(nextMonth) } },
        )
    }

    @Test
    fun `저번 달 이전은 카드 만료일을 생성할 수 없다`() {
        // given
        val lastMonth = YearMonth.now().minusMonths(1L)

        // when
        // then
        assertThrows<IllegalArgumentException> { CardExpiry(lastMonth) }
    }
}
