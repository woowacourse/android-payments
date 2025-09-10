package woowacourse.payments

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

@Suppress("ktlint:standard:function-naming")
class ExpiryValidatorTest {
    @Test
    fun 유효한_만료일을_입력하면_true를_반환한다() {
        // given
        val now = LocalDate.now()
        val month = now.month.value.toString()
        val year = now.year.toString().takeLast(2)
        val input = month + year
        val expiry = if (input.length == 3) "0$input" else input
        print(expiry)

        // when
        val actual = ExpiryValidator.isValidExpiry(expiry)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun 유효하지_않은_만료일을_입력하면_false를_반환한다() {
        // given
        val expiry = "0324"

        // when
        val actual = ExpiryValidator.isValidExpiry(expiry)

        // then
        assertThat(actual).isFalse()
    }
}
