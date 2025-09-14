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
import woowacourse.payments.ui.card.register.component.CardPasswordTextField

@RunWith(AndroidJUnit4::class)
class CardPasswordTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드_비밀번호는_4자리까지만_가능하다`() {
        // Given
        val text = "12345"
        val expected = "••••"

        // When
        composeTestRule.setContent {
            var password by remember { mutableStateOf("") }
            CardPasswordTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.testTag("CardPasswordTextField"),
            )
        }
        composeTestRule.onNodeWithTag("CardPasswordTextField").performTextInput(text)

        // Then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }

    @Test
    fun `카드_비밀번호는_숫자만_가능하다`() {
        // Given
        val text = "1aaa"
        val expected = "•"

        // When
        composeTestRule.setContent {
            var password by remember { mutableStateOf("") }
            CardPasswordTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.testTag("CardPasswordTextField"),
            )
        }
        composeTestRule.onNodeWithTag("CardPasswordTextField").performTextInput(text)

        // Then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }
}
