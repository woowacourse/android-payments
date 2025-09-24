package woowacourse.payments.cardaddition

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardAdditionStateHolderTest {
    private lateinit var stateHolder: CardAdditionStateHolder

    @BeforeEach
    fun setUp() {
        stateHolder = CardAdditionStateHolder()
    }

    @Test
    fun `카드_번호에_숫자가_아닌_값을_입력할_수_없다`() {
        // when
        stateHolder.updateCardNumber("123NaN")

        // then
        assertThat(stateHolder.uiState.card.number).isEqualTo("123")
    }

    @Test
    fun `카드_소유자_이름의_경우_입력_글자_제한이_30자이다`() {
        // when
        stateHolder.updateHolder("GIO".repeat(11))

        // then
        assertThat(stateHolder.uiState.card.holder).isEqualTo("GIO".repeat(10))
    }
}
