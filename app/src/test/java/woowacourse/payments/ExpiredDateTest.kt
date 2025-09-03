package woowacourse.payments

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import woowacourse.payments.domain.ExpiredDate

class ExpiredDateTest {
    @Test
    fun 만료일자는_1월부터_12월이_사이여야_한다() {
        // given
        val month = 13
        val year = 25

        // when & then 
        ExpiredDate.of(month, year) shouldBe null
    }

    @Test
    fun 만료일자는_25년_미만일_수_없다() {
        // given
        val month = 1
        val year = 21

        // when & then
        ExpiredDate.of(month, year) shouldBe null
    }
}
