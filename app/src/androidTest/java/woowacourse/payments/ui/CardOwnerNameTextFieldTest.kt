package woowacourse.payments.ui

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

class CardOwnerNameTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            var value by remember { mutableStateOf("") }
            CardOwnerNameTextField(
                value = value,
                onNameChange = { value = it },
                modifier = Modifier.testTag(TEST_TAG)
            )
        }
    }

    @Test
    fun `카드_소유자_이름의_길이는_30자를_넘을_수_없다`() {
        // given
        composeRule
            .onNodeWithTag(TEST_TAG)
            .performTextInput("모".repeat(30))

        // when
        composeRule
            .onNodeWithTag(TEST_TAG)
            .performTextInput("모찌")

        // then
        composeRule
            .onNodeWithTag(TEST_TAG)
            .assert(hasText("모".repeat(30)))
            .assertIsDisplayed()
    }

    @Test
    fun `입력된_이름의_길이가_출력된다`() {
        // when
        composeRule
            .onNodeWithTag(TEST_TAG)
            .performTextInput("모찌")

        // then
        composeRule
            .onNodeWithTag(TEST_TAG)
            .assert(hasText("2/30"))
            .assertIsDisplayed()
    }

    companion object {
        private const val TEST_TAG = "TEST_TAG"
    }
}
