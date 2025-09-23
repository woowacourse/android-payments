package woowacourse.payments.ui.screen.registration

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertAll
import woowacourse.payments.ui.fixture.PAYMENT_CARD
import woowacourse.payments.ui.model.PaymentCardUiModel
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
    fun `화면_진입_시_카드사_선택_바텀_시트가_보인다`() {
        // given
        setup()

        // then
        composeTestRule
            .onNodeWithContentDescription("카드사 선택 바텀 시트")
            .assertIsDisplayed()
    }

    @Test
    fun `카드사_선택_바텀_시트에서_카드사를_선택하면_카드사_이름이_카드_미리보기에_보인다`() {
        // given
        setup()

        // when
        composeTestRule
            .onNode(hasText("국민카드") and hasClickAction())
            .performClick()

        composeTestRule.waitForIdle()

        // then
        composeTestRule
            .onNodeWithContentDescription("카드")
            .assertTextContains("국민카드")
    }

    @Test
    fun `카드정보가_모두_정상으로_입력되면_카드_등록_버튼이_활성화된다`() {
        // given
        setup()

        // when
        composeTestRule
            .onNode(hasText("국민카드") and hasClickAction())
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("카드 번호 입력란", useUnmergedTree = true)
            .performTextInput("1234123412341234")
        composeTestRule
            .onNodeWithContentDescription("카드 만료일 입력란", useUnmergedTree = true)
            .performTextInput("1234")
        composeTestRule
            .onNodeWithContentDescription("카드 소유자 이름 입력란", useUnmergedTree = true)
            .performTextInput("DICE")
        composeTestRule
            .onNodeWithContentDescription("카드 비밀번호 입력란", useUnmergedTree = true)
            .performTextInput("1234")

        composeTestRule.waitForIdle()

        // then
        composeTestRule.onNodeWithRoleAndContentDescription(Role.Button, "카드 등록").assertIsEnabled()
    }

    @Test
    fun `이미_생성된_카드_정보를_가지고_진입한_경우_화면에_정보가_보인다`() {
        // given
        setup(initialUiState = CardRegistrationScreenUiState.from(PAYMENT_CARD))

        // then
        assertAll(
            {
                composeTestRule
                    .onNode(hasText("국민카드") and hasClickAction())
                    .assert(hasText("국민카드"))
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("카드 번호 입력란", useUnmergedTree = true)
                    .assert(hasText("1234 - 1234 - 1234 - 1234"))
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("카드 만료일 입력란", useUnmergedTree = true)
                    .assert(hasText("12 / 34"))
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("카드 소유자 이름 입력란", useUnmergedTree = true)
                    .assert(hasText("DICE"))
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("카드 비밀번호 입력란", useUnmergedTree = true)
                    .assert(hasText("••••"))
            },
        )
    }

    @Test
    fun `이미_생성된_카드_정보를_가지고_진입한_경우_변경사항이_존재하지_않으면_버튼이_비활성화_된다`() {
        // given
        setup(initialUiState = CardRegistrationScreenUiState.from(PAYMENT_CARD))

        // then
        composeTestRule
            .onNodeWithRoleAndContentDescription(Role.Button, "카드 등록")
            .assertIsNotEnabled()
    }

    @Test
    fun `이미_생성된_카드_정보를_가지고_진입한_경우_변경사항이_존재하면_버튼이_활성화_된다`() {
        // given
        setup(initialUiState = CardRegistrationScreenUiState.from(PAYMENT_CARD))

        // when
        composeTestRule
            .onNodeWithContentDescription("카드 소유자 이름 입력란", useUnmergedTree = true)
            .performTextInput("ICE")

        // then
        composeTestRule
            .onNodeWithRoleAndContentDescription(Role.Button, "카드 등록")
            .assertIsEnabled()
    }

    private fun setup(
        initialUiState: CardRegistrationScreenUiState? = null,
        onBackClick: () -> Unit = {},
        onRegisteredCard: (PaymentCardUiModel) -> Unit = {},
        onUpdatedCard: (PaymentCardUiModel) -> Unit = {},
    ) {
        composeTestRule.setContent {
            val viewModel =
                initialUiState?.let(::CardRegistrationScreenViewModel)
                    ?: CardRegistrationScreenViewModel()
            CardRegistrationScreen(
                viewModel = viewModel,
                onBackClick = onBackClick,
                onRegisteredCard = onRegisteredCard,
                onUpdatedCard = onUpdatedCard,
            )
        }
    }
}
