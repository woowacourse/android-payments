package woowacourse.payments.new

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.core.CompanyResourceProvider
import woowacourse.payments.ui.state.CardCompanyState
import woowacourse.payments.ui.view.new.NewCardScreen
import woowacourse.payments.ui.view.new.NewCardUiState

class NewCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            NewCardScreen(
                resourceProvider = CompanyResourceProvider(),
                onFinishRequest = {},
                uiState =
                    NewCardUiState(
                        number = "1234123412341234",
                        expireDate = "0908",
                        ownerName = "peto",
                        password = "0908",
                        CardCompanyState.Selected(CardCompany.BC),
                    ),
                onCardChange = {},
            )
        }
    }

    @Test
    fun `카드번호를_입력하면_구분자에_따라_자동으로_분리된다`() {
        // given
        val cardNumber = "1234123412341234"

        // when
        composeTestRule
            .onNode(hasText("카드 번호") and hasSetTextAction())
            .performTextInput(cardNumber)

        // then
        composeTestRule
            .onNodeWithText("1234 - 1234 - 1234 - 1234")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일을_입력하면_구분자로_분리된다`() {
        // given
        val expireDate = "0908"

        // when
        composeTestRule
            .onNode(
                hasText("만료일") and hasSetTextAction(),
            ).performTextInput(expireDate)

        // then
        composeTestRule
            .onNodeWithText("09 / 08")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_소유자의_이름_길이가_출력된다`() {
        // given
        val name = "peto"

        // when
        composeTestRule
            .onNode(
                hasText("카드 소유자 이름(선택)") and hasSetTextAction(),
            ).performTextInput(name)

        // then
        composeTestRule
            .onNodeWithText("4 / 30")
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호는_암호화된다`() {
        // given
        val password = "0908"

        // when
        composeTestRule
            .onNode(
                hasText("비밀번호") and hasSetTextAction(),
            ).performTextInput(password)

        // then
        composeTestRule
            .onNodeWithText("••••")
            .assertIsDisplayed()
    }
}
