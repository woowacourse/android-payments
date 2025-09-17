package woowacourse.payments.cards

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.serialization.toSerializationCard
import woowacourse.payments.ui.state.CardCompanyState
import woowacourse.payments.ui.view.cards.CardUiStateHolder
import woowacourse.payments.ui.view.cards.CardsUiState

class CardUiStateHolderTest {
    private val saver: Saver<CardUiStateHolder, Any> = CardUiStateHolder.Saver

    @Test
    fun `초기_상태는_비어있고_toolbarActionButtonVisibility_는_false이다`() {
        // given
        val holder = CardUiStateHolder()

        // then
        assertEquals(holder.uiState, CardsUiState.EMPTY)
        assertFalse(holder.toolbarActionButtonVisibility)
    }

    @Test
    fun `EMPTY_상태에서_카드가_추가되면_SINGLE_상태로_전환된다`() {
        // given
        val holder = CardUiStateHolder()
        val card = Card("1111", "12/34", "홍길동", "12", CardCompanyState.Selected(CardCompany.BC)).toSerializationCard()

        // when
        holder.addCard(card)

        // then
        assertTrue(holder.uiState is CardsUiState.SINGLE)
    }

    @Test
    fun `SINGLE_상태에서_카드가_추가되면_MULTIPLE_상태로_전환되고_toolbarActionButtonVisibility_상태가_true가된다`() {
        // given
        val firstCard = Card("1111", "12/34", "페토", "1234", CardCompanyState.Selected(CardCompany.BC)).toSerializationCard()
        val secondCard = Card("2222", "56/78", "정페토", "1234", CardCompanyState.Selected(CardCompany.BC)).toSerializationCard()
        val holder = CardUiStateHolder()

        // when
        holder.addCard(firstCard)
        holder.addCard(secondCard)

        // then
        assertTrue(holder.uiState is CardsUiState.MULTIPLE)
        val state = holder.uiState as CardsUiState.MULTIPLE
        assertEquals(state.state.size, 2)
        assertTrue(holder.toolbarActionButtonVisibility)
    }

    @Test
    fun `Saver로_SINGLE_상태를_저장하고_복원한다`() {
        // given
        val card = Card("1111", "12/34", "페토", "1234", CardCompanyState.Selected(CardCompany.BC))
        val holder = CardUiStateHolder(CardsUiState.SINGLE(card))
        val saved =
            with(
                object : SaverScope {
                    override fun canBeSaved(value: Any): Boolean = true
                },
            ) {
                saver.run { save(holder) }
            } ?: throw IllegalArgumentException("상태 저장 실패")

        // when
        val restored = saver.restore(saved)

        // then
        assertNotNull(restored)
        assertTrue(restored.uiState is CardsUiState.SINGLE)
        val state = restored.uiState as CardsUiState.SINGLE
        assertEquals(state.state, card)
    }

    @Test
    fun `Saver로_MULTIPLE_상태를_저장하고_복원한다`() {
        // given
        val card1 = Card("1111", "12/34", "페토", "1234", CardCompanyState.Selected(CardCompany.BC))
        val card2 = Card("2222", "56/78", "정페토", "1234", CardCompanyState.Selected(CardCompany.BC))
        val holder = CardUiStateHolder(CardsUiState.MULTIPLE(listOf(card1, card2)))

        val saved =
            with(
                object : SaverScope {
                    override fun canBeSaved(value: Any): Boolean = true
                },
            ) {
                saver.run { save(holder) }
            } ?: throw IllegalArgumentException("상태 저장 실패")

        // when
        val restored = saver.restore(saved)

        // then
        assertNotNull(restored)
        assertTrue(restored.uiState is CardsUiState.MULTIPLE)
        val state = restored.uiState as CardsUiState.MULTIPLE
        assertEquals(state.state, listOf(card1, card2))
    }
}
