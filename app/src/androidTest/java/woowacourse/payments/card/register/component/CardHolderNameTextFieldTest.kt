import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.payments.ui.card.register.component.CardHolderNameTextField

@RunWith(AndroidJUnit4::class)
class CardHolderNameTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드_소유자_이름은_영어_대문자만_가능하다`() {
        // Given
        val text = "name"
        val expected = "NAME"

        // When
        composeTestRule.setContent {
            var cardHolderName by remember { mutableStateOf("") }
            CardHolderNameTextField(
                value = cardHolderName,
                onValueChange = { cardHolderName = it },
            )
        }
        composeTestRule.onNodeWithTag("CardHolderNameTextField").performTextInput(text)

        // Then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }

    @Test
    fun `카드_소유자_이름은_30자를_초과하여_입력할_수_없다`() {
        // Given
        val text = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDE"
        val expected = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCD"

        // When
        composeTestRule.setContent {
            var cardHolderName by remember { mutableStateOf("") }
            CardHolderNameTextField(
                value = cardHolderName,
                onValueChange = { cardHolderName = it },
            )
        }
        composeTestRule.onNodeWithTag("CardHolderNameTextField").performTextInput(text)

        // Then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }

    @Test
    fun `supportingText는_입력한_글자수를_표시한다`() {
        // Given
        val text = "ABCDE"
        val expected = "5 / 30"

        // When
        composeTestRule.setContent {
            var cardHolderName by remember { mutableStateOf("") }
            CardHolderNameTextField(
                value = cardHolderName,
                onValueChange = { cardHolderName = it },
            )
        }
        composeTestRule.onNodeWithTag("CardHolderNameTextField").performTextInput(text)

        // Then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }
}
