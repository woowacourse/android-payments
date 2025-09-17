package woowacourse.payments.ui.card.register

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import woowacourse.payments.domain.Bank
import woowacourse.payments.domain.BankType

class RegisterCardStateHolderTest {
    @Test
    fun 카드사선택시_uiState가_업데이트되고_바텀시트가_닫힌다() {
        // Given
        val stateHolder = RegisterCardStateHolder {}
        val bank = Bank(BankType.KB, "국민카드")

        // When
        stateHolder.updateSelectedBank(bank)

        // Then
        assertEquals(bank, stateHolder.uiState.selectedBank)
        assertFalse(stateHolder.uiState.showBottomSheet)
    }

    @Test
    fun 카드사선택이_없으면_onCardSaved가_호출되지_않을_수_있다() {
        // Given
        var savedCalled = false
        val stateHolder = RegisterCardStateHolder { savedCalled = true }

        // When
        stateHolder.saveCard()

        // Then
        assertFalse(savedCalled)
    }

    @Test
    fun 입력값에따라_uiState가_업데이트될_수_있다() {
        // Given
        val stateHolder = RegisterCardStateHolder {}

        // When
        stateHolder.updateCardNumber("1234")
        stateHolder.updateExpirationDate("1124")
        stateHolder.updateCardHolderName("TAMA")
        stateHolder.updatePassword("12")

        // Then
        assertEquals("1234", stateHolder.uiState.cardNumber)
        assertEquals("1124", stateHolder.uiState.expirationDate)
        assertEquals("TAMA", stateHolder.uiState.cardHolderName)
        assertEquals("12", stateHolder.uiState.password)
    }
}
