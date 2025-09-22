package woowacourse.payments.ui.card.list

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import woowacourse.payments.ui.model.CardUiModel

class CardListStateHolderTest {
    @Test
    fun 초기상태는_uiState가_Empty이다() {
        // Given
        val stateHolder = CardListStateHolder()

        // Then
        assertTrue(stateHolder.uiState is CardListUiState.Empty)
    }

    @Test
    fun 한장의_카드가_있으면_uiState가_Single이다() {
        // Given
        val stateHolder = CardListStateHolder()
        val card = CardUiModel("1234", "11/24", "TAMA ONE", "국민카드", 0xFF333333)

        // When
        stateHolder.addNewCard(card)

        // Then
        assertTrue(stateHolder.uiState is CardListUiState.Single)

        val singleState = stateHolder.uiState as CardListUiState.Single
        assertEquals(card, singleState.card)
    }

    @Test
    fun 한장_이상의_카드가_있으면_uiState가_Multipe이다() {
        // Given
        val stateHolder = CardListStateHolder()
        val card1 = CardUiModel("1234", "11/24", "TAMA ONE", "국민카드", 0xFF333333)
        val card2 = CardUiModel("5678", "12/25", "TAMA TWO", "신한카드", 0xFF1565C0)

        // When
        stateHolder.addNewCard(card1)
        stateHolder.addNewCard(card2)

        // Then
        assertTrue(stateHolder.uiState is CardListUiState.Multiple)

        val multipleState = stateHolder.uiState as CardListUiState.Multiple
        assertEquals(listOf(card1, card2), multipleState.cards)
    }
}
