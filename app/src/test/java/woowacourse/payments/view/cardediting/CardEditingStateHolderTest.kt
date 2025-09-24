package woowacourse.payments.view.cardediting

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.CardUiModel

class CardEditingStateHolderTest {
    @Test
    fun `카드_수정_화면에서_변경사항이_발생하지_않으면_수정이_불가능하다`() {
        val stateHolder = CardEditingStateHolder(CardEditingUiState(CardUiModel))
        assertThat(stateHolder.uiState.canEditCard).isEqualTo(false)
    }
}
