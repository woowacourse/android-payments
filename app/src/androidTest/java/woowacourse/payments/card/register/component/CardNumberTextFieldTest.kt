package woowacourse.payments.card.register.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CardNumberTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            CardNumberTextField(modifier = Modifier.testTag("CardNumberTextField"))
        }
    }

    @Test
    fun `16자리_초과_입력_시_16자리로_잘리고_하이픈이_포함되어_보인다`() {
        // Given
        val text = "12341234123412345"
        val expected = "1234-1234-1234-1234"

        // When
        composeTestRule
            .onNodeWithTag("CardNumberTextField")
            .performTextInput(text)

        // Then
        composeTestRule
            .onNodeWithText(expected)
            .assertIsDisplayed()
    }

    @Test
    fun `문자가_포함된_입력은_숫자만_남고_하이픈이_포함되어_보인다`() {
        // Given
        val text = "12ab34cd56ef78gh"
        val expected = "1234-5678-"

        // When
        composeTestRule
            .onNodeWithTag("CardNumberTextField")
            .performTextInput(text)

        // Then
        composeTestRule
            .onNodeWithText(expected)
            .assertIsDisplayed()
    }
}
