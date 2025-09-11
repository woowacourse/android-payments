package woowacourse.payments.ui.screen.registration

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertAll
import woowacourse.payments.ui.fixture.VALIDATED_CARD_EXPIRATION_DATE
import woowacourse.payments.ui.fixture.VALIDATED_CARD_HOLDER_NAME
import woowacourse.payments.ui.fixture.VALIDATED_CARD_NUMBER
import woowacourse.payments.ui.fixture.VALIDATED_CARD_PASSWORD

class CardRegistrationScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `초기_화면에서_카드_등록_버튼은_비활성화된다`() {
        // given
        setup()
        val cardRegistrationButton =
            composeTestRule.onNodeWithRoleAndContentDescription(Role.Button, "완료")

        // then
        assertAll(
            { cardRegistrationButton.assertIsDisplayed() },
            { cardRegistrationButton.assertIsNotEnabled() },
        )
    }

    @Test
    fun `카드정보가_모두_정상이면_카드_등록_버튼이_활성화된다`() {
        // given
        setup(
            CardRegistrationScreenUiState(
                cardNumber = VALIDATED_CARD_NUMBER,
                cardExpirationDate = VALIDATED_CARD_EXPIRATION_DATE,
                cardholderName = VALIDATED_CARD_HOLDER_NAME,
                cardPassword = VALIDATED_CARD_PASSWORD,
            ),
        )
        val cardRegistrationButton =
            composeTestRule.onNodeWithRoleAndContentDescription(Role.Button, "완료")
        composeTestRule.waitForIdle()

        // then
        assertAll(
            { composeTestRule.onNodeWithText("1234 - 1234 - 1234 - 1234").assertIsDisplayed() },
            { composeTestRule.onNodeWithText("12 / 34").assertIsDisplayed() },
            { composeTestRule.onNodeWithText("DICE").assertIsDisplayed() },
            { composeTestRule.onNodeWithText("••••").assertIsDisplayed() },
            { cardRegistrationButton.assertIsDisplayed() },
            { cardRegistrationButton.assertIsEnabled() },
        )
    }

    private fun setup(initialUiState: CardRegistrationScreenUiState? = null) {
        composeTestRule.setContent {
            val viewModel =
                initialUiState?.let(::CardRegistrationScreenViewModel)
                    ?: CardRegistrationScreenViewModel()
            CardRegistrationScreen(
                viewModel = viewModel,
                onBackClick = {},
                onRegistrationComplete = {},
            )
        }
    }

    private fun ComposeContentTestRule.onNodeWithRoleAndContentDescription(
        role: Role,
        contentDescription: String,
    ) = onNode(
        SemanticsMatcher.expectValue(SemanticsProperties.Role, role)
            and
            hasContentDescription(contentDescription),
    )
}
