package woowacourse.payments.new

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.payments.ui.state.CardState
import woowacourse.payments.ui.view.new.NewCardMode
import woowacourse.payments.ui.view.new.NewCardUiEvent
import woowacourse.payments.ui.view.new.NewCardUiState
import woowacourse.payments.ui.view.new.NewCardUiStateHolder
import woowacourse.payments.ui.view.new.NewCardUiStateHolder.Companion.NewCardUiStateHolder

class NewCardUiStateHolderTest {
    @Test
    fun `카드_번호가_변경되면_OnChangeCardNumber_이벤트가_발생하고_카드번호가_변경된다`() {
        // given
        val holder = NewCardUiStateHolder(NewCardMode.Add)

        // when
        holder.modifyUiState(NewCardUiEvent.OnChangeCardNumber("12345678"))

        // then
        assertEquals(holder.uiState.number, "12345678")
    }

    @Test
    fun `카드_만료일이_변경되면_OnChangeExpireDate_이벤트가_발생하고_만료일이_변경된다`() {
        // given
        val holder = NewCardUiStateHolder(NewCardMode.Add)

        // when
        holder.modifyUiState(NewCardUiEvent.OnChangeExpireDate("1228"))

        // then
        assertEquals(holder.uiState.expireDate, "1228")
    }

    @Test
    fun `카드_소유자_이름이_변경되면_OnChangeOwnerName_이벤트가_발생하고_소유자_이름이_변경된다`() {
        // given
        val holder = NewCardUiStateHolder(NewCardMode.Add)

        // when
        holder.modifyUiState(NewCardUiEvent.OnChangeOwnerName("홍길동"))

        // then
        assertEquals(holder.uiState.ownerName, "홍길동")
    }

    @Test
    fun `카드_비밀번호가_변경되면_OnChangePassword_이벤트가_발생하고_비밀번호가_변경된다`() {
        // given
        val holder = NewCardUiStateHolder(NewCardMode.Add)

        // when
        holder.modifyUiState(NewCardUiEvent.OnChangePassword("12"))

        // then
        assertEquals(holder.uiState.password, "12")
    }

    @Test
    fun `Saver를_통해_상태가_저장된_후_복원된다`() {
        val holder = NewCardUiStateHolder(NewCardMode.Add)
        holder.modifyUiState(NewCardUiEvent.OnChangeCardNumber("12345678"))
        holder.modifyUiState(NewCardUiEvent.OnChangeExpireDate("0908"))
        holder.modifyUiState(NewCardUiEvent.OnChangeOwnerName("페토"))
        holder.modifyUiState(NewCardUiEvent.OnChangePassword("1234"))

        val saver: Saver<NewCardUiStateHolder, NewCardUiState> = NewCardUiStateHolder.Saver

        val saved: NewCardUiState =
            with(
                object : SaverScope {
                    override fun canBeSaved(value: Any): Boolean = true
                },
            ) {
                saver.run { save(holder) } as NewCardUiState
            }

        // restore
        val restored = NewCardUiStateHolder.Saver.restore(saved)

        restored?.uiState?.let {
            assertEquals(it.number, "12345678")
            assertEquals(it.expireDate, "0908")
            assertEquals(it.ownerName, "페토")
            assertEquals(it.password, "1234")
            assertEquals(it.mode, NewCardMode.Add)
        }
    }
}
