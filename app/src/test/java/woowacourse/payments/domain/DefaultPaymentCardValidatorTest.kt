package woowacourse.payments.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import woowacourse.payments.domain.PaymentCardValidator.PaymentCardValidationResult
import java.time.YearMonth

class DefaultPaymentCardValidatorTest {
    private val validator = DefaultPaymentCardValidator()

    // ==================== 카드 번호 ====================
    @Test
    @DisplayName("카드 번호가 16자리일 때 VALID 처리된다")
    fun cardNumber_valid() {
        // given
        val cardNumber = "1234567890123456"

        // when
        val result = validator.validateCardNumber(cardNumber)

        // then
        assertEquals(PaymentCardValidationResult.VALID, result)
    }

    @Test
    @DisplayName("카드 번호가 4자리일 때 NOT_FILLED 처리된다")
    fun cardNumber_tooShort() {
        // given
        val cardNumber = "1234"

        // when
        val result = validator.validateCardNumber(cardNumber)

        // then
        assertEquals(PaymentCardValidationResult.NOT_FILLED, result)
    }

    @Test
    @DisplayName("카드 번호가 17자리일 때 NOT_FILLED 처리된다")
    fun cardNumber_tooLong() {
        // given
        val cardNumber = "12345678901234567"

        // when
        val result = validator.validateCardNumber(cardNumber)

        // then
        assertEquals(PaymentCardValidationResult.NOT_FILLED, result)
    }

    @Test
    @DisplayName("카드 번호가 비어있을 때 NOT_FILLED 처리된다")
    fun cardNumber_empty() {
        // given
        val cardNumber = ""

        // when
        val result = validator.validateCardNumber(cardNumber)

        // then
        assertEquals(PaymentCardValidationResult.NOT_FILLED, result)
    }

    // ==================== 카드 만료일 ====================
    @Test
    @DisplayName("현재 연도/월 카드 만료일은 VALID 처리된다")
    fun expirationDate_current() {
        // given
        val cardExpirationDate = "12${YearMonth.now().year % 100}"

        // when
        val result = validator.validateCardExpirationDate(cardExpirationDate)

        // then
        assertEquals(PaymentCardValidationResult.VALID, result)
    }

    @Test
    @DisplayName("다음 연도 카드 만료일은 VALID 처리된다")
    fun expirationDate_nextYear() {
        // given
        val cardExpirationDate = "01${(YearMonth.now().year + 1) % 100}"

        // when
        val result = validator.validateCardExpirationDate(cardExpirationDate)

        // then
        assertEquals(PaymentCardValidationResult.VALID, result)
    }

    @Test
    @DisplayName("지난 연도 카드 만료일은 INVALID 처리된다")
    fun expirationDate_lastYear() {
        // given
        val cardExpirationDate = "01${(YearMonth.now().year - 1) % 100}"

        // when
        val result = validator.validateCardExpirationDate(cardExpirationDate)

        // then
        assertEquals(PaymentCardValidationResult.INVALID, result)
    }

    @ParameterizedTest
    @ValueSource(strings = ["0010", "1310"])
    @DisplayName("잘못된 월 입력은 INVALID 처리된다")
    fun expirationDate_invalidMonth(date: String) {
        // given & when
        val result = validator.validateCardExpirationDate(date)

        // then
        assertEquals(PaymentCardValidationResult.INVALID, result)
    }

    @ParameterizedTest
    @ValueSource(strings = ["A123", "12AA", "MMYY"])
    @DisplayName("숫자가 아닌 값 입력은 INVALID 처리된다")
    fun expirationDate_notNumber(date: String) {
        // given & when
        val result = validator.validateCardExpirationDate(date)

        // then
        assertEquals(PaymentCardValidationResult.INVALID, result)
    }

    @ParameterizedTest
    @ValueSource(strings = ["123", "12345", ""])
    @DisplayName("길이 문제는 NOT_FILLED 처리된다")
    fun expirationDate_wrongLength(date: String) {
        // given & when
        val result = validator.validateCardExpirationDate(date)

        // then
        assertEquals(PaymentCardValidationResult.NOT_FILLED, result)
    }

    @Test
    @DisplayName("먼 미래 날짜는 VALID 처리된다")
    fun expirationDate_farFuture() {
        // given
        val cardExpirationDate = "1299"

        // when
        val result = validator.validateCardExpirationDate(cardExpirationDate)

        // then
        assertEquals(PaymentCardValidationResult.VALID, result)
    }

    // ==================== 카드 소유자 이름 ====================
    @Test
    @DisplayName("카드 소유자 이름이 일반 문자열일 때 VALID 처리된다")
    fun cardholderName_normal() {
        // given
        val name = "홍길동"

        // when
        val result = validator.validateCardholderName(name)

        // then
        assertEquals(PaymentCardValidationResult.VALID, result)
    }

    @Test
    @DisplayName("카드 소유자 이름이 비어있을 때 VALID 처리된다")
    fun cardholderName_empty() {
        // given
        val name = ""

        // when
        val result = validator.validateCardholderName(name)

        // then
        assertEquals(PaymentCardValidationResult.VALID, result)
    }

    @Test
    @DisplayName("특수문자가 포함된 이름도 VALID 처리된다")
    fun cardholderName_withSpecialChars() {
        // given
        val name = "홍!길@동#"

        // when
        val result = validator.validateCardholderName(name)

        // then
        assertEquals(PaymentCardValidationResult.VALID, result)
    }

    // ==================== 카드 비밀번호 ====================
    @Test
    @DisplayName("카드 비밀번호가 4자리일 때 VALID 처리된다")
    fun cardPassword_valid() {
        // given
        val password = "1234"

        // when
        val result = validator.validateCardPassword(password)

        // then
        assertEquals(PaymentCardValidationResult.VALID, result)
    }

    @Test
    @DisplayName("카드 비밀번호가 2자리일 때 NOT_FILLED 처리된다")
    fun cardPassword_tooShort() {
        // given
        val password = "12"

        // when
        val result = validator.validateCardPassword(password)

        // then
        assertEquals(PaymentCardValidationResult.NOT_FILLED, result)
    }

    @Test
    @DisplayName("카드 비밀번호가 5자리일 때 NOT_FILLED 처리된다")
    fun cardPassword_tooLong() {
        // given
        val password = "12345"

        // when
        val result = validator.validateCardPassword(password)

        // then
        assertEquals(PaymentCardValidationResult.NOT_FILLED, result)
    }

    @Test
    @DisplayName("카드 비밀번호가 비어있을 때 NOT_FILLED 처리된다")
    fun cardPassword_empty() {
        // given
        val password = ""

        // when
        val result = validator.validateCardPassword(password)

        // then
        assertEquals(PaymentCardValidationResult.NOT_FILLED, result)
    }
}
