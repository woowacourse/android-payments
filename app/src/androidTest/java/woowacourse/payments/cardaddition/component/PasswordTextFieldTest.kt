package woowacourse.payments.cardaddition.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
            val (password: String, setPassword: (String) -> Unit) = remember { mutableStateOf("") }

            PasswordTextField(
                value = password,
                onValueChange = setPassword,
                isError = false,
            )
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
}
