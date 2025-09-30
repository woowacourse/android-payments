package woowacourse.payments.view.cardediting

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.CardUiModel
import woowacourse.payments.view.ui.model.BankTypeUiModel

class CardEditingStateHolderTest {
    private lateinit var stateHolder: CardEditingStateHolder

    @BeforeEach
    fun setUp() {
        stateHolder =
            CardEditingStateHolder(
                CardEditingUiState(CardUiModel),
            )
    }

    @Test
    fun `카드_번호에_숫자가_아닌_값을_입력할_수_없다`() {
        // when
        stateHolder.updateCardNumber("123NaN")

        // then
        assertThat(stateHolder.uiState.edited.number).isEqualTo("123")
    }

    @Test
    fun `카드_비밀번호는_4글자까지_입력할_수_있다`() {
        // when
        stateHolder.updatePassword("12345678")

        // then
        assertThat(stateHolder.uiState.edited.password).isEqualTo("1234")
    }

    @Test
    fun `카드_소유자_이름의_경우_입력_글자_제한이_30자이다`() {
        // when
        stateHolder.updateHolder("GIO".repeat(11))

        // then
        assertThat(stateHolder.uiState.edited.holder).isEqualTo("GIO".repeat(10))
    }

    @Test
    fun `선택한_카드사에_따라_카드_미리보기가_바뀌어야_한다`() {
        // when
        stateHolder.updateBankType(BankTypeUiModel.BC)

        // then
        assertThat(stateHolder.uiState.edited.bankType).isEqualTo(BankTypeUiModel.BC)
    }

    @Test
    fun `카드_수정_화면에서_변경사항이_발생하지_않으면_수정이_불가능하다`() {
        val stateHolder = CardEditingStateHolder(CardEditingUiState(CardUiModel))
        assertThat(stateHolder.uiState.canEditCard).isEqualTo(false)
    }
}
