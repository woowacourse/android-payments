package woowacourse.payments.cards

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.serialization.toSerializationCard
import woowacourse.payments.ui.view.cards.CardUiStateHolder
import woowacourse.payments.ui.view.cards.CardsUiState

class CardUiStateHolderTest {
    private val saver: Saver<CardUiStateHolder, Any> = CardUiStateHolder.Saver

    @Test
    fun `초기_상태는_비어있고_toolbarActionButtonVisibility_는_false이다`() {
        val holder = CardUiStateHolder()
        assertEquals(holder.uiState, CardsUiState.EMPTY)
        assertFalse(holder.toolbarActionButtonVisibility)
    }

    @Test
    fun `EMPTY_상태에서_카드가_추가되면_SINGLE_상태로_전환된다`() {
        val holder = CardUiStateHolder()
        val card = Card("1111", "12/34", "홍길동", "12").toSerializationCard()

        holder.addCard(card)

        assertTrue(holder.uiState is CardsUiState.SINGLE)
    }

    @Test
    fun `SINGLE_상태에서_카드가_추가되면_MULTIPLE_상태로_전환되고_toolbarActionButtonVisibility_상태가_true가된다`() {
        val firstCard = Card("1111", "12/34", "페토", "1234").toSerializationCard()
        val secondCard = Card("2222", "56/78", "정페토", "1234").toSerializationCard()

        val holder = CardUiStateHolder()
        holder.addCard(firstCard)
        holder.addCard(secondCard)

        assertTrue(holder.uiState is CardsUiState.MULTIPLE)
        val state = holder.uiState as CardsUiState.MULTIPLE
        assertEquals(state.state.size, 2)
        assertTrue(holder.toolbarActionButtonVisibility)
    }

    @Test
    fun `Saver로_EMPTY_상태를_저장하고_복원한다`() {
        val holder = CardUiStateHolder(CardsUiState.EMPTY)
        val saved =
            with(
                object : SaverScope {
                    override fun canBeSaved(value: Any): Boolean = true
                },
            ) {
                saver.run { save(holder) }
            } ?: throw IllegalArgumentException("상태 저장 실패")

        saver.restore(saved)?.let {
            assertEquals(CardsUiState.EMPTY, it.uiState)
        }
    }

    @Test
    fun `Saver로_SINGLE_상태를_저장하고_복원한다`() {
        val card = Card("1111", "12/34", "페토", "1234")
        val holder = CardUiStateHolder(CardsUiState.SINGLE(card))
        val saved =
            with(
                object : SaverScope {
                    override fun canBeSaved(value: Any): Boolean = true
                },
            ) {
                saver.run { save(holder) }
            } ?: throw IllegalArgumentException("상태 저장 실패")
        val restored = saver.restore(saved)

        assertTrue(restored!!.uiState is CardsUiState.SINGLE)
        val state = restored.uiState as CardsUiState.SINGLE
        assertEquals(state.state, card)
    }

    @Test
    fun `Saver로_MULTIPLE_상태를_저장하고_복원한다`() {
        val card1 = Card("1111", "12/34", "페토", "1234")
        val card2 = Card("2222", "56/78", "정페토", "1234")
        val holder = CardUiStateHolder(CardsUiState.MULTIPLE(listOf(card1, card2)))

        val saved =
            with(
                object : SaverScope {
                    override fun canBeSaved(value: Any): Boolean = true
                },
            ) {
                saver.run { save(holder) }
            } ?: throw IllegalArgumentException("상태 저장 실패")

        val restored = saver.restore(saved)

        assertTrue(restored!!.uiState is CardsUiState.MULTIPLE)
        val state = restored.uiState as CardsUiState.MULTIPLE
        assertEquals(state.state, listOf(card1, card2))
    }
}
