package woowacourse.payments.cards

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.payments.CARD
import woowacourse.payments.InstantTaskExecutorExtension
import woowacourse.payments.getOrAwaitValue

@ExtendWith(InstantTaskExecutorExtension::class)
class CardsViewModelTest {
    private lateinit var viewModel: CardsViewModel

    @BeforeEach
    fun setUp() {
        viewModel = CardsViewModel()
    }

    @Test
    fun `새로운_카드를_추가하면_UI_이벤트를_발생시킨다`() {
        // when
        viewModel.addCard(CARD)

        // then
        assertThat(viewModel.event.getOrAwaitValue().content).isEqualTo(CardsUiEvent.AddCardSuccess)
    }
}
