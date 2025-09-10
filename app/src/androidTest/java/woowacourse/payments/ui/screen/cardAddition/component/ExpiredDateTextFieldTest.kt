package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
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
            ExpiredDateTextField(
                value = value,
                onDateChange = { value = it },
                modifier = Modifier.testTag(TEST_TAG),
            )
        }
    }

    @Test
    fun `만료일의_달과_년도는_구분자로_구분된다`() {
        // when
        composeRule
            .onNodeWithTag(TEST_TAG)
            .performTextInput("1225")

        // then
        composeRule
            .onNodeWithTag(TEST_TAG)
            .assert(hasText("12 / 25"))
            .assertIsDisplayed()
    }

    @Test
    fun `만료일은_4글자이다`() {
        // when
        composeRule
            .onNodeWithTag(TEST_TAG)
            .performTextInput("12255")

        // then
        composeRule
            .onNodeWithTag(TEST_TAG)
            .assert(hasText("12 / 25"))
            .assertIsDisplayed()
    }

    @Test
    fun `만료일에_숫자가_아닌_값을_입력할_수_없다`() {
        // when
        composeRule
            .onNodeWithTag(TEST_TAG)
            .performTextInput("12a!25")

        // then
        composeRule
            .onNodeWithTag(TEST_TAG)
            .assert(hasText("12 / 25"))
            .assertIsDisplayed()
    }

    companion object {
        private const val TEST_TAG = "TEST_TAG"
    }
}
