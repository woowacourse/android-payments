package woowacourse.payments.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class ExpirationDateTest {
    @Test
    fun `올바른 형식의 미래 날짜는 생성에 성공한다`() {
        val future = YearMonth.now().plusMonths(1)
        val formatted = future.format(DateTimeFormatter.ofPattern("MM/yy"))

        val expirationDate = ExpirationDate(formatted)
        assertEquals(formatted, expirationDate.expirationDate)
    }

    @Test
    fun `형식이 MMYY가 아니면 예외 발생`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                ExpirationDate("2025-12")
            }
        assertEquals("만료일은 'MM/YY' 형식이어야 합니다.", exception.message)
    }

    @Test
    fun `월이 13 이상이면 예외 발생`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                ExpirationDate("13/25")
            }
        assertEquals("만료일은 'MM/YY' 형식이어야 합니다.", exception.message)
    }

    @Test
    fun `만료일이 현재 이전이면 예외 발생`() {
        val past = YearMonth.now().minusMonths(1)
        val formatted = past.format(DateTimeFormatter.ofPattern("MM/yy"))

        val exception =
            assertThrows<IllegalArgumentException> {
                ExpirationDate(formatted)
            }
        assertEquals("만료된 카드입니다.", exception.message)
    }
}
