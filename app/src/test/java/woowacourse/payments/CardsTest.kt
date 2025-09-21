package woowacourse.payments

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.Card
import java.time.YearMonth

class CardsTest {
    @Test
    fun `숫자 외 문자 카드번호 입력시 예외가 발생한다`() {
        // given
        val result =
            Card.create(
                cardNumber = "뭉치바보",
                expiryDate = YearMonth.of(2034, 12),
                cardOwner = "뭉치",
                password = "1234",
                bankType = BankType.BC,
            )

        // when & then
        val exception = assertThrows<IllegalArgumentException> { result.getOrThrow() }
        assertNotNull(exception.message)
        Assertions.assertTrue(exception.message!!.contains("카드번호 오류"))
    }

    @Test
    fun `카드번호 16자리 초과 시 예외가 발생한다`() {
        // given
        val cardNumber = "1".repeat(17)
        val result =
            Card.create(
                cardNumber = cardNumber,
                expiryDate = YearMonth.of(2034, 12),
                cardOwner = "뭉치",
                password = "1234",
                bankType = BankType.BC,
            )

        // when & then
        val exception = assertThrows<IllegalArgumentException> { result.getOrThrow() }
        assertNotNull(exception.message)
        Assertions.assertTrue(exception.message!!.contains("카드번호 오류"))
    }

    @Test
    fun `만료일이 현재보다 과거일 시 예외가 발생한다`() {
        // given
        val result =
            Card.create(
                cardNumber = "1234567812345678",
                expiryDate = YearMonth.of(2024, 12),
                cardOwner = "뭉치",
                password = "1234",
                bankType = BankType.BC,
            )

        // when & then
        val exception = assertThrows<IllegalArgumentException> { result.getOrThrow() }
        assertNotNull(exception.message)
        Assertions.assertTrue(exception.message!!.contains("만료일 오류"))
    }

    @Test
    fun `숫자 외 문자 비밀번호 입력 시 예외가 발생한다`() {
        // given
        val result =
            Card.create(
                cardNumber = "1234567812345678",
                expiryDate = YearMonth.of(2034, 12),
                cardOwner = "뭉치",
                password = "뭉치바보",
                bankType = BankType.BC,
            )

        // when & then
        val exception = assertThrows<IllegalArgumentException> { result.getOrThrow() }
        assertNotNull(exception.message)
        Assertions.assertTrue(exception.message!!.contains("비밀번호 오류"))
    }

    @Test
    fun `비밀번호 4자리 초과 시 예외가 발생한다`() {
        // given
        val result =
            Card.create(
                cardNumber = "1234567812345678",
                expiryDate = YearMonth.of(2034, 12),
                cardOwner = "뭉치",
                password = "12345",
                bankType = BankType.BC,
            )

        // when & then
        val exception = assertThrows<IllegalArgumentException> { result.getOrThrow() }
        assertNotNull(exception.message)
        Assertions.assertTrue(exception.message!!.contains("비밀번호 오류"))
    }

    @ParameterizedTest
    @EnumSource(value = BankType::class, names = ["NONE"], mode = EnumSource.Mode.EXCLUDE)
    fun `은행타입을 선택해야 카드를 생성할 수 있다`(bankType: BankType) {
        // given
        val result =
            Card.create(
                cardNumber = "1234567812345678",
                expiryDate = YearMonth.of(2034, 12),
                cardOwner = "뭉치",
                password = "1234",
                bankType = bankType,
            )

        // when & then
        assertDoesNotThrow { result.getOrThrow() }
    }

    @Test
    fun `은행을 선택하지 않으면 예외가 발생한다`() {
        // given
        val result =
            Card.create(
                cardNumber = "1234567812345678",
                expiryDate = YearMonth.of(2034, 12),
                cardOwner = "뭉치",
                password = "1234",
                bankType = BankType.NONE,
            )

        // when & then
        assertThrows<IllegalArgumentException> { result.getOrThrow() }
    }
}
