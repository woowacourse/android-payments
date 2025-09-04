package woowacourse.payments.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            var value by remember { mutableStateOf("") }
            PasswordTextField(value = value, onPasswordChange = { value = it })
        }
    }

    @Test
    fun `입력한_비밀번호는_가려진다`() {
        // given
        val expected = "\u2022".repeat(4)

        // when
        composeRule
            .onNodeWithText("")
            .performTextInput("1234")

        // then
        composeRule
            .onNodeWithText(expected)
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_4글자이다() {
        // when
        composeRule
            .onNodeWithText("")
            .performTextInput("12345678")

        // then
        composeRule
            .onNodeWithText("\u2022".repeat(4))
            .assertIsDisplayed()
    }
}
