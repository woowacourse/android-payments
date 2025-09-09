package woowacourse.payments.cardaddition.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardAdditionScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            CardAdditionScreen()
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
    fun `카드_번호에_숫자가_아닌_값을_입력할_수_없다`() {
        // when
        composeRule
            .onNodeWithTag("CardNumberTextField")
            .performTextInput("123NaN")

        // then
        composeRule
            .onNodeWithText("123")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_소유자_이름의_경우_입력_글자_제한이_30자이다`() {
        // given
        composeRule
            .onNodeWithTag("CardOwnerNameTextField")
            .performTextInput("GIO".repeat(10))

        // when
        composeRule
            .onNodeWithText("GIO".repeat(10))
            .performTextInput("GIO")

        // then
        composeRule
            .onNodeWithText("GIO".repeat(10))
            .assertIsDisplayed()
    }

    @Test
    fun `카드_비밀번호는_4글자까지_입력할_수_있다`() {
        // when
        composeRule
            .onNodeWithTag("PasswordTextField")
            .performTextInput("12345678")

        // then
        composeRule
            .onNodeWithText("\u2022".repeat(4))
            .assertIsDisplayed()
    }
}
