package woowacourse.payments.ui

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
            ExpiredDateTextField()
        }
    }

    @Test
    fun `만료일의_달과_년도는_구분자로_구분된다`() {
        // when
        composeRule
            .onNodeWithText("")
            .performTextInput("1225")

        // then
        composeRule
            .onNodeWithText("12 / 25")
            .assertIsDisplayed()
    }
}
