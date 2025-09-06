package woowacourse.payments.ui.newcard.componenets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.newcard.components.PasswordField

class PasswordTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            var text: String by remember { mutableStateOf("") }

            PasswordField(
                modifier = Modifier,
                value = text,
                onValueChange = { text = it },
                maxLength = 4,
            )
        }
    }

    @Test
    fun `비밀번호가_라벨로_보인다`() {
        // then
        composeTestRule
            .onNode(hasText("비밀번호") and hasSetTextAction())
            .assertExists()
    }

    @Test
    fun `입력칸을_클릭하면_기본값이_보인다`() {
        // given
        val textField = composeTestRule.onNode(hasText("비밀번호"))

        // when
        textField
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("0000")
            .assertExists()
    }

    @Test
    fun `비밀번호를_입력하면_노출되지_않는다`() {
        // given
        val textField = composeTestRule.onNode(hasText("비밀번호"))

        // when
        textField.performTextInput("1234")

        // then
        composeTestRule
            .onNodeWithText("••••")
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호는_최대_길이까지만_입력_가능하다`() {
        // given
        val textField = composeTestRule.onNode(hasText("비밀번호"))

        // when
        repeat(30) {
            textField.performTextInput("1")
        }

        // then
        composeTestRule
            .onNodeWithText("••••")
            .assertIsDisplayed()
    }
}
