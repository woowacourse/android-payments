package woowacourse.payments.cards

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.serialization.toSerializationCard
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
        val card = Card("1111", "12/34", "홍길동", "12", CardCompany.BC).toSerializationCard()

        // when
        holder.addCard(card)

        // then
        assertTrue(holder.uiState is CardsUiState.SINGLE)
    }

    @Test
    fun `SINGLE_상태에서_카드가_추가되면_MULTIPLE_상태로_전환되고_toolbarActionButtonVisibility_상태가_true가된다`() {
        // given
        val firstCard = Card("1111", "12/34", "페토", "1234", CardCompany.BC).toSerializationCard()
        val secondCard = Card("2222", "56/78", "정페토", "1234", CardCompany.BC).toSerializationCard()
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
        val card = Card("1111", "12/34", "페토", "1234", CardCompany.BC)
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
        val card1 = Card("1111", "12/34", "페토", "1234", CardCompany.BC)
        val card2 = Card("2222", "56/78", "정페토", "1234", CardCompany.BC)
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

    @Test
    fun `카드가_한장일_때_상태가_수정된다`() {
        // given
        val holder =
            CardUiStateHolder(
                CardsUiState.SINGLE(
                    state =
                        Card(
                            "1111",
                            "12/34",
                            "페토",
                            "1234",
                            CardCompany.BC,
                        ),
                ),
            )

        // when
        holder.modifyCardAt(
            0,
            Card(
                "2222",
                "12/24",
                "정페토",
                "4567",
                CardCompany.WOORI,
            ).toSerializationCard(),
        )

        // then
        assertTrue(holder.uiState is CardsUiState.SINGLE)
        val state = holder.uiState as CardsUiState.SINGLE
        assertEquals(state.state.number, "2222")
        assertEquals(state.state.expireDate, "12/24")
        assertEquals(state.state.ownerName, "정페토")
        assertEquals(state.state.password, "4567")
        assertEquals(state.state.company, CardCompany.WOORI)
    }

    @Test
    fun `카드가_3장일_때_두_번째_카드가_수정된다`() {
        // given
        val index = 1
        val holder =
            CardUiStateHolder(
                CardsUiState.MULTIPLE(
                    listOf(
                        Card(
                            "1111",
                            "12/34",
                            "페토",
                            "1234",
                            CardCompany.BC,
                        ),
                        Card(
                            "222",
                            "12/34",
                            "페토",
                            "1234",
                            CardCompany.BC,
                        ),
                        Card(
                            "3333",
                            "12/34",
                            "박찬호",
                            "1234",
                            CardCompany.BC,
                        ),
                    ),
                ),
            )

        // when
        holder.modifyCardAt(
            index,
            Card(
                "0000",
                "09/08",
                "정찬호",
                "0908",
                CardCompany.KB,
            ).toSerializationCard(),
        )

        // then
        assertTrue(holder.uiState is CardsUiState.MULTIPLE)
        val state = (holder.uiState as CardsUiState.MULTIPLE).state[index]

        assertEquals(state.number, "0000")
        assertEquals(state.expireDate, "09/08")
        assertEquals(state.ownerName, "정찬호")
        assertEquals(state.password, "0908")
        assertEquals(state.company, CardCompany.KB)
    }
}
