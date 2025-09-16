import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.payments.ui.card.register.component.CardNumberTextField

@RunWith(AndroidJUnit4::class)
class CardNumberTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `16자리_초과_입력_시_16자리로_잘리고_하이픈이_포함되어_보인다`() {
        // Given
        val text = "12341234123412345"
        val expected = "1234-1234-1234-1234"

        // When
        composeTestRule.setContent {
            var cardNumber by remember { mutableStateOf("") }
            CardNumberTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                modifier = Modifier.testTag("CardNumberTextField"),
            )
        }
        composeTestRule.onNodeWithTag("CardNumberTextField").performTextInput(text)

        // Then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }

    @Test
    fun `문자가_포함된_입력은_숫자만_남고_하이픈이_포함되어_보인다`() {
        // Given
        val text = "12ab34cd56ef78gh"
        val expected = "1234-5678-78"

        // When
        composeTestRule.setContent {
            var cardNumber by remember { mutableStateOf("") }
            CardNumberTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                modifier = Modifier.testTag("CardNumberTextField"),
            )
        }
        composeTestRule.onNodeWithTag("CardNumberTextField").performTextInput(text)

        // Then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }
}
