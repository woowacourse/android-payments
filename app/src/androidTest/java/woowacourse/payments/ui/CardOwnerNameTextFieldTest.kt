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

class CardOwnerNameTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            var value by remember { mutableStateOf("") }
            CardOwnerNameTextField(value = value, onNameChange = { value = it })
        }
    }

    @Test
    fun `카드_소유자_이름의_길이는_30자를_넘을_수_없다`() {
        // given
        composeRule
            .onNodeWithText("")
            .performTextInput("모".repeat(30))

        // when
        composeRule
            .onNodeWithText("모".repeat(30))
            .performTextInput("모찌")

        // then
        composeRule
            .onNodeWithText("모".repeat(30))
            .assertIsDisplayed()
    }

    @Test
    fun `입력된_이름의_길이가_출력된다`() {
        // given
        val name = "모찌"

        // when
        composeRule
            .onNodeWithText("")
            .performTextInput(name)

        // then
        composeRule
            .onNodeWithText("2/30")
            .assertIsDisplayed()
    }
}
