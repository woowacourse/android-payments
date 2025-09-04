package woowacourse.payments.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.payments.CardPasswordTextField

class CardPasswordTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            var cardPassword by remember { mutableStateOf("") }
            CardPasswordTextField(cardPassword = cardPassword) { newValue ->
                cardPassword = newValue
            }
        }
    }

    @Test
    fun `비밀번호는_숫자만_입력_가능해야_한다`() {
        // when
        val textField = composeTestRule.onNodeWithTag("CardPasswordTextField")
        textField.performTextInput("1")
        textField.performTextInput("a")
        textField.performTextInput("2")

        // then
        composeTestRule
            .onNodeWithTag("CardPasswordTextField", useUnmergedTree = true)
            .assertTextEquals("••")
    }

    @Test
    fun `비밀번호_입력_값이_없는_경우_Placeholder가_보여진다`() {
        // when
        composeTestRule
            .onNodeWithTag("CardPasswordTextField", useUnmergedTree = true)
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("0000")
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호는_길이가_4자를_넘어갈_수_없다`() {
        // when
        composeTestRule
            .onNodeWithTag("CardPasswordTextField")
            .performTextInput("12345")

        // then
        composeTestRule
            .onNodeWithTag("CardPasswordTextField", useUnmergedTree = true)
            .assertTextEquals("••••")
    }
}
