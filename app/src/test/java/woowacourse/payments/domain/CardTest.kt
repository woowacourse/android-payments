package woowacourse.payments.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CardTest {
    @Test
    fun `올바른 값으로 카드를 생성하면 성공한다`() {
        // given
        val validNumber = "1234567890123456"
        val validExpiration =
            YearMonth
                .now()
                .plusYears(1)
                .format(DateTimeFormatter.ofPattern("MM/yy"))
        val validName = "TAMA SEO"
        val validPassword = "1234"
        val bank = Bank(BankType.KB, "국민카드")

        // when
        val result = Card.newCard(validNumber, validExpiration, validName, validPassword, bank)

        // then
        result.onSuccess { card ->
            assertEquals(validNumber, card.cardNumber.number)
            assertEquals(validExpiration, card.expirationDate.expirationDate)
            assertEquals(validName, card.cardHolderName.cardHolderName)
            assertEquals(validPassword, card.password.password)
            assertEquals(bank, card.bank)
        }
    }

    @Test
    fun `잘못된 카드 번호로 카드를 생성하면 예외 발생`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                Card.newCard("1234", "12/99", "TAMA SEO", "1234", Bank(BankType.KB, "국민카드"))
            }
        assertEquals("카드 번호는 16자리 숫자여야 합니다.", exception.message)
    }

    @Test
    fun `만료된 카드로 생성하면 예외 발생`() {
        val expired =
            YearMonth
                .now()
                .minusMonths(1)
                .format(DateTimeFormatter.ofPattern("MM/yy"))

        val exception =
            assertThrows<IllegalArgumentException> {
                Card.newCard("1234567890123456", expired, "TAMA SEO", "1234", Bank(BankType.KB, "국민카드"))
            }
        assertEquals("만료된 카드입니다.", exception.message)
    }

    @Test
    fun `잘못된 카드 소지자 이름으로 생성하면 예외 발생`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                Card.newCard("1234567890123456", "12/99", "TAMA_SEO", "1234", Bank(BankType.KB, "국민카드"))
            }
        assertEquals("카드 소지자 이름은 대문자 알파벳으로만 구성되어야 합니다.", exception.message)
    }

    @Test
    fun `잘못된 비밀번호로 생성하면 예외 발생`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                Card.newCard("1234567890123456", "12/99", "TAMA SEO", "12a4", Bank(BankType.KB, "국민카드"))
            }
        assertEquals("비밀번호는 4자리 숫자여야 합니다.", exception.message)
    }
}
