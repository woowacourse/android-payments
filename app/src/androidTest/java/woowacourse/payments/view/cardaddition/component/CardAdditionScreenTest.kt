package woowacourse.payments.view.cardaddition.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.view.cardaddition.CardAdditionUiState
import woowacourse.payments.view.ui.model.CardUiModel

class CardAdditionScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    private fun setUp(cardUiModel: CardUiModel = CardUiModel()) {
        composeRule.setContent {
            CardAdditionScreen(
                state = CardAdditionUiState(cardUiModel),
                onUiEvent = {},
            )
        }
    }

    @Test
    fun `카드_번호_입력_후_키보드의_다음_버튼을_누르면_만료일_입력창으로_넘어간다`() {
        // given
        setUp()

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
        // given
        setUp(
            cardUiModel =
                CardUiModel(
                    number = "1234".repeat(3),
                ),
        )

        // then
        composeRule
            .onNodeWithTag("CardNumberTextFieldSupportingText", useUnmergedTree = true)
            .assertTextEquals("올바른 형식이 아닙니다.")
    }

    @Test
    fun `만료일_입력_후_키보드의_다음_버튼을_누르면_카드_소유자_이름_입력창으로_넘어간다`() {
        // given
        setUp()

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
        // given
        setUp(
            cardUiModel =
                CardUiModel(
                    expiredDate = "042",
                ),
        )

        // then
        composeRule
            .onNodeWithTag("ExpiredDateTextFieldSupportingText", useUnmergedTree = true)
            .assertTextEquals("올바른 형식이 아닙니다.")
    }

    @Test
    fun `만료_달이_올바르지_않을_경우_에러_메시지를_출력한다`() {
        // given
        setUp(
            cardUiModel =
                CardUiModel(
                    expiredDate = "1325",
                ),
        )

        // then
        composeRule
            .onNodeWithTag("ExpiredDateTextFieldSupportingText", useUnmergedTree = true)
            .assertTextEquals("올바른 형식이 아닙니다.")
    }

    @Test
    fun `카드_소유자_이름_입력_후_키보드의_다음_버튼을_누르면_비밀번호_입력창으로_넘어간다`() {
        // given
        setUp()

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
        // given
        setUp(
            cardUiModel =
                CardUiModel(
                    password = "123",
                ),
        )

        // then
        composeRule
            .onNodeWithTag("PasswordTextFieldSupportingText", useUnmergedTree = true)
            .assertTextEquals("올바른 형식이 아닙니다.")
    }

    @Test
    fun `카드_추가_화면에_접속했을_때_카드사_선택_다이얼로그가_표시된다`() {
        // given
        setUp()

        composeRule
            .onNodeWithTag("BankSelectBottomSheet")
            .assertIsDisplayed()
    }
}
