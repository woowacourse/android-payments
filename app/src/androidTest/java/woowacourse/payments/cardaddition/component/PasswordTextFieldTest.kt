package woowacourse.payments.cardaddition.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            PasswordTextField()
        }
    }

    @Test
    fun `비밀번호는_노출되어서는_안_된다`() {
        // when
        composeRule
            .onNodeWithText("")
            .performTextInput("1234")

        // then
        composeRule
            .onNodeWithText("\u2022".repeat(4))
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호는_4글자까지_입력할_수_있다`() {
        // when
        composeRule
            .onNodeWithText("")
            .performTextInput("12345678")

        // then
        composeRule
            .onNodeWithText("\u2022".repeat(4))
            .assertIsDisplayed()
    }

    @Test
    fun `형식이_올바르지_않을_경우_에러_메시지를_출력한다`() {
        // when
        composeRule
            .onNodeWithText("")
            .performTextInput("12")

        // then
        composeRule
            .onNodeWithText("올바른 형식이 아닙니다.")
            .assertIsDisplayed()
    }
}
