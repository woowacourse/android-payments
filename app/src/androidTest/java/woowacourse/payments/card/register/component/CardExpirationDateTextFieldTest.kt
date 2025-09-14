package woowacourse.payments.card.register.component

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
import woowacourse.payments.ui.card.register.component.CardExpirationDateTextField

@RunWith(AndroidJUnit4::class)
class CardExpirationDateTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드_만료일은_4자리까지만_입력이_가능하다`() {
        // Given
        val text = "112432"
        val expected = "11 / 24"

        // When
        composeTestRule.setContent {
            var expirationDate by remember { mutableStateOf("") }
            CardExpirationDateTextField(
                value = expirationDate,
                onValueChange = { expirationDate = it },
                modifier = Modifier.testTag("CardExpirationDateTextField"),
            )
        }
        composeTestRule.onNodeWithTag("CardExpirationDateTextField").performTextInput(text)

        // Then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }

    @Test
    fun `만료일은_숫자만_입력_가능하다`() {
        // Given
        val text = "11a24b"
        val expected = "11 / 24"

        // When
        composeTestRule.setContent {
            var expirationDate by remember { mutableStateOf("") }
            CardExpirationDateTextField(
                value = expirationDate,
                onValueChange = { expirationDate = it },
                modifier = Modifier.testTag("CardExpirationDateTextField"),
            )
        }
        composeTestRule.onNodeWithTag("CardExpirationDateTextField").performTextInput(text)

        // Then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }

    @Test
    fun `만료일_입력시_MM_YY_형식으로_표시된다`() {
        // Given
        val text = "1124"
        val expected = "11 / 24"

        // When
        composeTestRule.setContent {
            var expirationDate by remember { mutableStateOf("") }
            CardExpirationDateTextField(
                value = expirationDate,
                onValueChange = { expirationDate = it },
                modifier = Modifier.testTag("CardExpirationDateTextField"),
            )
        }
        composeTestRule.onNodeWithTag("CardExpirationDateTextField").performTextInput(text)

        // Then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }
}
