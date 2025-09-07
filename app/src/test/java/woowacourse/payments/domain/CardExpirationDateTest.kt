package woowacourse.payments.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class CardExpirationDateTest {
    @Test
    fun `유효한 만료일(미래)일 경우 true를 반환한다`() {
        // given
        val futureDate = LocalDate.now().plusMonths(1)
        val month = String.format("%02d", futureDate.monthValue)
        val year = futureDate.year.toString().takeLast(2)
        val cardExpirationDate = CardExpirationDate(month, year)

        // when
        val actual = cardExpirationDate.isValid()

        // then
        assertTrue(actual)
    }

    @Test
    fun `유효한 만료일(현재)일 경우 true를 반환한다`() {
        // given
        val currentDate = LocalDate.now()
        val month = String.format("%02d", currentDate.monthValue)
        val year = currentDate.year.toString().takeLast(2)
        val cardExpirationDate = CardExpirationDate(month, year)

        // when
        val actual = cardExpirationDate.isValid()

        // then
        assertTrue(actual)
    }

    @Test
    fun `지난 만료일일 경우 false를 반환한다`() {
        // given
        val pastDate = LocalDate.now().minusMonths(1)
        val month = String.format("%02d", pastDate.monthValue)
        val year = pastDate.year.toString().takeLast(2)
        val cardExpirationDate = CardExpirationDate(month, year)

        // when
        val actual = cardExpirationDate.isValid()

        // then
        assertFalse(actual)
    }

    @Test
    fun `월이 12를 초과할 경우 false를 반환한다`() {
        // given
        val cardExpirationDate = CardExpirationDate("13", "25")

        // when
        val actual = cardExpirationDate.isValid()

        // then
        assertFalse(actual)
    }

    @Test
    fun `월 또는 년도가 2자리가 아닐 경우 false를 반환한다`() {
        // given
        val cardExpirationDate = CardExpirationDate("1", "25")

        // when
        val actual = cardExpirationDate.isValid()

        // then
        assertFalse(actual)
    }

    @Test
    fun `월과 년도가 비어있을 경우 true를 반환한다`() {
        // given
        val cardExpirationDate = CardExpirationDate("", "")

        // when
        val actual = cardExpirationDate.isValid()

        // then
        assertTrue(actual)
    }

    @Test
    fun `onValueChange는 날짜 문자열을 올바르게 월과 년으로 분리한다`() {
        // given
        val date = "1225"
        val cardExpirationDate = CardExpirationDate("", "")

        // when
        val actual = cardExpirationDate.fromRawInput(date)

        // then
        assertEquals("12", actual.month)
        assertEquals("25", actual.year)
    }
}
