package woowacourse.payments.ui.screen.registration

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardPasswordUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel

class CardRegistrationScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun `초기_화면에서_저장버튼은_비활성화된다`() {
        // given
        setup()

        // then
        composeTestRule
            .onNodeWithContentDescription(context.getString(R.string.icon_save_content_description))
            .assertIsDisplayed()
            .assertIsNotEnabled()
    }

    @Test
    fun `카드정보를_모두입력하면_저장버튼이_활성화된다`() {
        // given
        val uiState =
            CardRegistrationScreenUiState(
                cardNumber = CardNumberUiModel("1234567812345678"),
                cardExpirationDate = CardExpirationDateUiModel("1299"),
                cardholderName = CardholderNameUiModel("JANGHUN MUN"),
                cardPassword = CardPasswordUiModel("1234"),
            )
        setup(uiState)

        // then
        composeTestRule
            .onNodeWithContentDescription(context.getString(R.string.icon_save_content_description))
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    private fun setup(uiState: CardRegistrationScreenUiState? = null) {
        composeTestRule.setContent {
            CardRegistrationScreen(
                viewModel =
                    uiState?.let(::CardRegistrationScreenViewModel)
                        ?: CardRegistrationScreenViewModel(),
                onBackClick = {},
                onRegistrationComplete = {},
            )
        }
    }
}
