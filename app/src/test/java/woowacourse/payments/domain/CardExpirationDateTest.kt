package woowacourse.payments.domain

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CardExpirationDateTest {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMyy")

    @Test
    fun `과거 날짜일 경우 만료되었다`() {
        // given
        val pastDate = CardExpirationDate(YearMonth.of(2020, 12))

        // when
        val result = pastDate.isExpired(YearMonth.of(2025, 9))

        // then
        result shouldBe true
    }

    @Test
    fun `미래 날짜일 경우 만료되지 않았다`() {
        // given
        val futureDate = CardExpirationDate(YearMonth.of(2030, 1))

        // when
        val result = futureDate.isExpired(YearMonth.of(2025, 9))

        // then
        result shouldBe false
    }

    @Test
    fun `현재 날짜와 같을 경우 만료되지 않았다`() {
        // given
        val currentDate = CardExpirationDate(YearMonth.of(2025, 9))

        // when
        val result = currentDate.isExpired(YearMonth.of(2025, 9))

        // then
        result shouldBe false
    }

    @Test
    fun `올바른 날짜이면 정상적으로 변환된다`() {
        // given
        val dateString = "1225"

        // when
        val cardExpirationDate = CardExpirationDate.from(dateString, formatter)

        // then
        cardExpirationDate.date shouldBe YearMonth.of(2025, 12)
    }

    @Test
    fun `잘못된 날짜이면 예외가 발생한다`() {
        // given
        val dateString = "0000"

        // when & then
        shouldThrowAny {
            CardExpirationDate.from(dateString, formatter)
        }
    }
}
