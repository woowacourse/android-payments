package woowacourse.payments.newcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.newcard.component.PasswordTextField

class PasswordTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `비밀번호_입력란을_누르면_플레이스홀더가_보인다`() {
        // given
        composeTestRule.setContent {
            var value by remember { mutableStateOf("") }
            PasswordTextField(
                value = value,
                onValueChange = { value = it },
            )
        }

        // when
        composeTestRule.onNodeWithText("비밀번호").performClick()

        // then
        composeTestRule.onNodeWithText("0000").assertIsDisplayed()
    }

    @Test
    fun `비밀번호_입력란에는_문자는_입력되지_않는다`() {
        // given
        composeTestRule.setContent {
            var value by remember { mutableStateOf("") }
            PasswordTextField(
                value = value,
                onValueChange = { value = it },
            )
        }
        val textFieldNode = composeTestRule.onNode(hasSetTextAction(), useUnmergedTree = true)

        // when
        textFieldNode.performTextInput("크림")

        // then
        textFieldNode.assertTextEquals("")
    }

    @Test
    fun `비밀번호_입력해도_숫자가_노출되지_않는다`() {
        // given
        composeTestRule.setContent {
            var value by remember { mutableStateOf("") }
            PasswordTextField(
                value = value,
                onValueChange = { value = it },
            )
        }
        val textFieldNode = composeTestRule.onNode(hasSetTextAction(), useUnmergedTree = true)

        // when
        textFieldNode.performTextInput("1234")

        // then
        textFieldNode.assertTextEquals("••••")
    }
}
