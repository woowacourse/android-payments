package woowacourse.payments.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardValidatorTest {
    @Test
    fun `카드 번호가 16자리 숫자면 유효하다고 판단해 true 값을 반환한다`() {
        // given
        val validNumber = "1234567812345678"

        // when
        val result: Boolean = CardValidator.isValidNumber(validNumber)

        // then
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun `카드 번호가 16자리가 아니면 유효하지 않다고 판단해 false 값을 반환한다`() {
        // given
        val invalidNumber = "1234"

        // when
        val result = CardValidator.isValidNumber(invalidNumber)

        // then
        assertThat(result).isEqualTo(false)
    }

    @ParameterizedTest
    @ValueSource(strings = ["0130", "1231", "0203"])
    fun `만료일이 실제로 존재하면 유효하다고 판단해 true 값을 반환한다`(expiredDate: String) {
        // given
        // when
        val result = CardValidator.isValidExpiredDate(expiredDate)

        // then
        assertThat(result).isEqualTo(true)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1333", "1430", "4545"])
    fun `만료일이 실제로 존재하지 않는 날이면 유효하지 않다고 판단해 false 값을 반환한다`(expiredDate: String) {
        // given
        // when
        val result = CardValidator.isValidExpiredDate(expiredDate)

        // then
        assertThat(result).isEqualTo(false)
    }

    @ParameterizedTest
    @ValueSource(strings = ["0825", "0124", "1119"])
    fun `만료일이 오늘 날짜 기준으로 과거일 경우 유효하지 않다고 판단해 false 값을 반환한다`(expiredDate: String) {
        // given
        // when
        val result = CardValidator.isValidExpiredDate(expiredDate)

        // then
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun `비밀번호가 4자리 숫자면 유효하다고 판단해 true 값을 반환한다`() {
        // given
        val password = "1234"

        // when
        val result = CardValidator.isValidPassword(password)

        // then
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun `비밀번호가 숫자가 아닌 문자가 포함되면 유효하지 않다고 판단해 false 값을 반환한다`() {
        // given
        val password = "12a4"

        // when
        val result = CardValidator.isValidPassword(password)

        // then
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun `카드번호, 만료일, 비밀번호 모두 유효하면 카드도 유효하기에 true 값을 반환한다`() {
        // given
        val number = "1234567812345678"
        val expiredDate = "1231"
        val password = "9876"

        // when
        val result = CardValidator.isValidCard(number, expiredDate, password)

        // then
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun `카드번호, 만료일, 패스워드 중 하나라도 잘못되면 카드 전체가 유효하지 않아 false 값을 반환한다`() {
        // given
        val number = "123"
        val expiredDate = "1231"
        val password = "9876"

        // when
        val result = CardValidator.isValidCard(number, expiredDate, password)

        // then
        assertThat(result).isEqualTo(false)
    }
}
