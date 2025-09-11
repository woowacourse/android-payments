package woowacourse.payments.ui.screen.cards

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertAll
import woowacourse.payments.R
import woowacourse.payments.ui.fixture.PAYMENT_CARD
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun `카드_목록이_빈_상태라면_안내_메시지와_등록_버튼이_보여진다`() {
        // given
        setup(CardsUiState.EMPTY)

        // then
        assertAll(
            {
                composeTestRule
                    .onNodeWithText(context.getString(R.string.cards_screen_registration_message))
                    .assertIsDisplayed()
            },
            {
                composeTestRule
                    .onNodeWithContentDescription(context.getString(R.string.cards_screen_registration_button_content_description))
                    .assertIsDisplayed()
            },
        )
    }

    @Test
    fun `카드_목록이_단일_카드_상태라면_카드_정보와_등록_버튼이_보여진다`() {
        // given
        setup(CardsUiState.SINGLE(PAYMENT_CARD))

        // then
        assertAll(
            { composeTestRule.onNodeWithText("DICE").assertIsDisplayed() },
            {
                composeTestRule
                    .onNodeWithContentDescription(context.getString(R.string.cards_screen_registration_button_content_description))
                    .assertIsDisplayed()
            },
        )
    }

    @Test
    fun `카드_목록이_다중_카드_상태라면_모든_카드_정보가_보여진다`() {
        // given
        val cards =
            listOf(
                PAYMENT_CARD,
                PAYMENT_CARD.copy(cardholderName = CardholderNameUiModel("BICE", 30)),
            )
        setup(CardsUiState.MULTIPLE(cards))

        // then
        assertAll(
            { composeTestRule.onNodeWithText("DICE").assertIsDisplayed() },
            { composeTestRule.onNodeWithText("BICE").assertIsDisplayed() },
        )
    }

    private fun setup(uiState: CardsUiState) {
        composeTestRule.setContent {
            CardsScreen(viewModel = CardsViewModel(uiState))
        }
    }
}
