package woowacourse.payments.domain

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.YearMonth

class ExpiredDateTest {
    @DisplayName("만료일 날짜가 생성된다")
    @Test
    fun createExpiredDateTest() {
        // given
        val dateString = "0925"

        // when
        val expiredDate = ExpiredDate(dateString)

        // then
        assertSoftly(expiredDate) {
            value shouldBe dateString
            formattedDate.getOrNull() shouldBe YearMonth.of(2025, 9)
            isValid shouldBe true
        }
    }

    @DisplayName("과거의 날짜를 입력하면 false를 반환한다")
    @Test
    fun pastExpiredDateTest() {
        // given
        val dateString = "0825"

        // when
        val expiredDate = ExpiredDate(dateString)

        // then
        assertSoftly(expiredDate) {
            formattedDate.shouldBeSuccess()
            isValid shouldBe false
        }
    }

    @DisplayName("범위를 벗어난 날짜를 입력하면 실패하며 false를 반환한다")
    @Test
    fun invalidExpiredDateTest() {
        // given
        val dateString = "2225"

        // when
        val expiredDate = ExpiredDate(dateString)

        // then
        assertSoftly(expiredDate) {
            formattedDate.shouldBeFailure()
            isValid shouldBe false
        }
    }

    @DisplayName("월과 연도를 모두 입력하지 않으면 실패하며 false를 반환한다")
    @Test
    fun shortExpiredDateTest() {
        // given
        val dateString = "925"

        // when
        val expiredDate = ExpiredDate(dateString)

        // then
        assertSoftly(expiredDate) {
            formattedDate.shouldBeFailure()
            isValid shouldBe false
        }
    }
}
