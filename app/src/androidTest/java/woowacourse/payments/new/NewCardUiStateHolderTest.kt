package woowacourse.payments.new

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.serialization.SerializationCard
import woowacourse.payments.ui.view.new.NewCardUiEvent
import woowacourse.payments.ui.view.new.NewCardUiStateHolder

class NewCardUiStateHolderTest {

    @Test
    fun `초기_상태는_카드가_비어있디`() {
        val holder = NewCardUiStateHolder()

        assertEquals(holder.uiState.card, Card.EMPTY)
    }

    @Test
    fun `카드_번호가_변경되면_OnChangeCardNumber_이벤트가_발생하고_카드번호가_변경된다`() {
        // given
        val holder = NewCardUiStateHolder()

        // when
        holder.updateCard(NewCardUiEvent.OnChangeCardNumber("12345678"))

        // then
        assertEquals(holder.uiState.card.number, "12345678")
    }

    @Test
    fun `카드_만료일이_변경되면_OnChangeExpireDate_이벤트가_발생하고_만료일이_변경된다`() {
        // given
        val holder = NewCardUiStateHolder()

        // when
        holder.updateCard(NewCardUiEvent.OnChangeExpireDate("1228"))

        // then
        assertEquals(holder.uiState.card.expireDate, "1228")
    }

    @Test
    fun `카드_소유자_이름이_변경되면_OnChangeOwnerName_이벤트가_발생하고_소유자_이름이_변경된다`() {
        // given
        val holder = NewCardUiStateHolder()

        // when
        holder.updateCard(NewCardUiEvent.OnChangeOwnerName("홍길동"))

        // then
        assertEquals(holder.uiState.card.ownerName, "홍길동")
    }

    @Test
    fun `카드_비밀번호가_변경되면_OnChangePassword_이벤트가_발생하고_비밀번호가_변경된다`() {
        // given
        val holder = NewCardUiStateHolder()

        // when
        holder.updateCard(NewCardUiEvent.OnChangePassword("12"))

        // then
        assertEquals(holder.uiState.card.password, "12")
    }

    @Test
    fun `Saver를_통해_상태가_저장된_후_복원된다`() {
        val holder = NewCardUiStateHolder()
        holder.updateCard(NewCardUiEvent.OnChangeCardNumber("12345678"))
        holder.updateCard(NewCardUiEvent.OnChangeExpireDate("0908"))
        holder.updateCard(NewCardUiEvent.OnChangeOwnerName("페토"))
        holder.updateCard(NewCardUiEvent.OnChangePassword("1234"))

        val saver: Saver<NewCardUiStateHolder, SerializationCard> = NewCardUiStateHolder.Saver

        val saved: SerializationCard? = with(object : SaverScope {
            override fun canBeSaved(value: Any): Boolean = true
        }) {
            saver.run { save(holder) }
        }

        assertEquals(saved?.number, "12345678")
        assertEquals(saved?.expireDate, "0908")
        assertEquals(saved?.ownerName, "페토")
        assertEquals(saved?.password, "1234")

        // restore
        val restored = NewCardUiStateHolder.Saver.restore(saved!!)

        assertEquals(restored?.uiState?.card?.number, "12345678")
        assertEquals(restored?.uiState?.card?.expireDate, "0908")
        assertEquals(restored?.uiState?.card?.ownerName, "페토")
        assertEquals(restored?.uiState?.card?.password, "1234")
    }
}

