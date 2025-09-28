package woowacourse.payments.view.cardediting.component

import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextReplacement
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.view.cardediting.CardEditingUiState
import woowacourse.payments.view.ui.model.BankTypeUiModel
import woowacourse.payments.view.ui.model.CardUiModel

class CardEditingScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            CardEditingScreen(
                state =
                    CardEditingUiState(
                        original =
                            CardUiModel(
                                number = "1234".repeat(4),
                                expiredDate = "0421",
                                holder = "CREW",
                                holderMaxLength = 30,
                                password = "1234",
                                bankType = BankTypeUiModel.BC,
                            ),
                    ),
                onUiEvent = {},
            )
        }
    }

    @Test
    fun `카드_번호_입력_후_키보드의_다음_버튼을_누르면_만료일_입력창으로_넘어간다`() {
        // when
        composeRule
            .onNodeWithText("카드 번호")
            .performImeAction()

        // then
        composeRule
            .onNodeWithText("만료일")
            .assertIsFocused()
    }

    @Test
    fun `카드_번호_형식이_올바르지_않을_경우_에러_메시지를_출력한다`() {
        // when
        composeRule
            .onNodeWithTag("CardNumberTextField")
            .performTextReplacement("")

        Thread.sleep(10000)

        // then
        composeRule
            .onNodeWithText("올바른 형식이 아닙니다.", useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun `만료일_입력_후_키보드의_다음_버튼을_누르면_카드_소유자_이름_입력창으로_넘어간다`() {
        // when
        composeRule
            .onNodeWithText("만료일")
            .performImeAction()

        // then
        composeRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertIsFocused()
    }

    @Test
    fun `만료일이_4자리가_아닐_경우_에러_메시지를_출력한다`() {
        // when
        composeRule
            .onNodeWithText("만료일")
            .performTextReplacement("12")

        // then
        composeRule
            .onNodeWithTag("ExpiredDateTextFieldSupportingText")
            .assertTextEquals("올바른 형식이 아닙니다.")
    }

    @Test
    fun `만료_달이_올바르지_않을_경우_에러_메시지를_출력한다`() {
        // when
        composeRule
            .onNodeWithText("만료일")
            .performTextReplacement("1325")

        // then
        composeRule
            .onNodeWithTag("ExpiredDateTextFieldSupportingText")
            .assertTextEquals("올바른 형식이 아닙니다.")
    }

    @Test
    fun `카드_소유자_이름_입력_후_키보드의_다음_버튼을_누르면_비밀번호_입력창으로_넘어간다`() {
        // when
        composeRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performImeAction()

        // then
        composeRule
            .onNodeWithText("비밀번호")
            .assertIsFocused()
    }

    @Test
    fun `비밀번호_형식이_올바르지_않을_경우_에러_메시지를_출력한다`() {
        // when
        composeRule
            .onNodeWithText("비밀번호")
            .performTextReplacement("12")

        // then
        composeRule
            .onNodeWithTag("PasswordTextFieldSupportingText")
            .assertTextEquals("올바른 형식이 아닙니다.")
    }
}
