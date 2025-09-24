package woowacourse.payments.ui.submitcard

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.submitcard.textfields.PasscodeTextField

@Suppress("ktlint:standard:function-naming")
class PasscodeTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        val stateHolder = SubmitCardScreenUiStateHolder()
        composeTestRule.setContent {
            PasscodeTextField(
                stateHolder.passcode,
                stateHolder.isPasscodeError,
                { newValue: String -> stateHolder.onPasscodeChanged(newValue) },
            )
        }
    }

    @Test
    fun 비밀번호가_4자_미만이면_경고_메시지가_표시된다() {
        // given
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("비밀번호")

        // when
        target.performTextInput("0")

        // then
        composeTestRule.onNodeWithText("비밀번호는 숫자 4자입니다.").assertIsDisplayed()
    }

    @Test
    fun 비밀번호를_4자_넘게_입력할_경우_첫_4자만_표시된다() {
        // given
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("비밀번호")

        // when
        target.performTextInput("01234")

        // then
        composeTestRule.onNodeWithText("0123").assertIsDisplayed()
    }

    @Test
    fun 비밀번호에_숫자가_아닌_문자가_있으면_경고_메시지가_표시된다() {
        // given
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("비밀번호")

        // when
        target.performTextInput("abcd")

        // then
        composeTestRule.onNodeWithText("비밀번호는 숫자 4자입니다.").assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_마스킹_처리된다() {
        // given
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("비밀번호")

        // when
        target.performTextInput("0123")

        // then
        composeTestRule.onNodeWithText("••••").assertIsDisplayed()
    }
}
