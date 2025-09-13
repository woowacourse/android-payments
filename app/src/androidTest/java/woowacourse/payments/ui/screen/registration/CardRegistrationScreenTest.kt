package woowacourse.payments.ui.screen.registration

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertAll
import woowacourse.payments.ui.fixture.VALIDATED_CARD_EXPIRATION_DATE
import woowacourse.payments.ui.fixture.VALIDATED_CARD_HOLDER_NAME
import woowacourse.payments.ui.fixture.VALIDATED_CARD_NUMBER
import woowacourse.payments.ui.fixture.VALIDATED_CARD_PASSWORD
import woowacourse.payments.ui.util.onNodeWithRoleAndContentDescription

class CardRegistrationScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `초기_화면에서_카드_등록_버튼은_비활성화된다`() {
        // given
        setup()
        val cardRegistrationButton =
            composeTestRule.onNodeWithRoleAndContentDescription(Role.Button, "카드 등록")

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
            composeTestRule.onNodeWithRoleAndContentDescription(Role.Button, "카드 등록")
        composeTestRule.waitForIdle()

        // then
        assertAll(
            {
                composeTestRule
                    .onNodeWithContentDescription("카드 번호 입력란", useUnmergedTree = true)
                    .assertTextEquals("1234 - 1234 - 1234 - 1234")
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("카드 만료일 입력란", useUnmergedTree = true)
                    .assertTextEquals("12 / 34")
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("카드 소유자 이름 입력란", useUnmergedTree = true)
                    .assertTextEquals("DICE")
            },
            {
                composeTestRule
                    .onNodeWithText("••••")
                    .assertIsDisplayed()
            },
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
}
