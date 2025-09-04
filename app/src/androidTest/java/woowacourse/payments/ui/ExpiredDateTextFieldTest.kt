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

class ExpiredDateTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            var value by remember { mutableStateOf("") }
            ExpiredDateTextField(value = value, onDateChange = { value = it })
        }
    }

    @Test
    fun `만료일의_달과_년도는_구분자로_구분된다`() {
        // given
        val expected = "12 / 25"

        // when
        composeRule
            .onNodeWithText("")
            .performTextInput("1225")

        // then
        composeRule
            .onNodeWithText(expected)
            .assertIsDisplayed()
    }
}
