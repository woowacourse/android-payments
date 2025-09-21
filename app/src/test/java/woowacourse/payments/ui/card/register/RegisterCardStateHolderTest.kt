package woowacourse.payments.ui.card.register

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import woowacourse.payments.domain.Bank
import woowacourse.payments.domain.BankType
import kotlin.test.assertTrue

class RegisterCardStateHolderTest {
    @Test
    fun `초기_상태는_모든_필드가_기본값으로_설정되어_있어야_한다`() {
        // Given
        val stateHolder = RegisterCardStateHolder {}
        val uiState = stateHolder.uiState

        // Then
        assertEquals("", uiState.cardNumber)
        assertEquals("", uiState.expirationDate)
        assertEquals("", uiState.cardHolderName)
        assertEquals("", uiState.password)
        assertEquals(null, uiState.selectedBank)
        assertTrue(uiState.showBottomSheet)
        assertEquals(null, uiState.toastMessage)
    }

    @Test
    fun `updateCardNumber_호출_시_uiState_cardNumber가_업데이트되어야_한다`() {
        // Given
        val stateHolder = RegisterCardStateHolder {}

        // When
        stateHolder.updateCardNumber("1234")

        // Then
        assertEquals("1234", stateHolder.uiState.cardNumber)
    }

    @Test
    fun `updateExpirationDate_호출_시_uiState_expirationDate가_업데이트되어야_한다`() {
        // Given
        val stateHolder = RegisterCardStateHolder {}

        // When
        stateHolder.updateExpirationDate("1124")

        // Then
        assertEquals("1124", stateHolder.uiState.expirationDate)
    }

    @Test
    fun `updateCardHolderName_호출_시_uiState_cardHolderName이_업데이트되어야_한다`() {
        // Given
        val stateHolder = RegisterCardStateHolder {}

        // When
        stateHolder.updateCardHolderName("TAMA")

        // Then
        assertEquals("TAMA", stateHolder.uiState.cardHolderName)
    }

    @Test
    fun `updatePassword_호출_시_uiState_password가_업데이트되어야_한다`() {
        // Given
        val stateHolder = RegisterCardStateHolder {}

        // When
        stateHolder.updatePassword("12")

        // Then
        assertEquals("12", stateHolder.uiState.password)
    }

    @Test
    fun `updateSelectedBank_호출_시_uiState_selectedBank가_업데이트된다`() {
        // Given
        val stateHolder = RegisterCardStateHolder {}
        val bank = Bank(BankType.KB, "국민카드")

        // When
        stateHolder.updateSelectedBank(bank)

        // Then
        assertEquals(bank, stateHolder.uiState.selectedBank)
    }

    @Test
    fun `updateSelectedBank_호출_시_uiState_showBottomSheet가_false로_변경된다`() {
        // Given
        val stateHolder = RegisterCardStateHolder {}
        val bank = Bank(BankType.KB, "국민카드")

        // When
        stateHolder.updateSelectedBank(bank)

        // Then
        assertFalse(stateHolder.uiState.showBottomSheet)
    }

    @Test
    fun `카드사_선택이_없으면_saveCard_호출_시_onCardSaved가_호출되지_않아야_하고_toastMessage가_설정되어야_한다`() {
        // Given
        var savedCalled = false
        val stateHolder = RegisterCardStateHolder { savedCalled = true }

        // When
        stateHolder.saveCard()

        // Then
        assertFalse(savedCalled)
        assertEquals("카드 생성에 실패했습니다.", stateHolder.uiState.toastMessage)
    }
}
