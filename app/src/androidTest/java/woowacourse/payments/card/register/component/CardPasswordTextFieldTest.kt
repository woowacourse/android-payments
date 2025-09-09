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
import woowacourse.payments.ui.card.register.component.CardPasswordTextField

@RunWith(AndroidJUnit4::class)
class CardPasswordTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            CardPasswordTextField(modifier = Modifier.testTag("CardPasswordTextField"))
        }
    }

    @Test
    fun `카드_비밀번호는_4자리까지만_가능하다`() {
        // Given
        val text = "12345"
        val expected = "••••"

        // When
        composeTestRule.onNodeWithTag("CardPasswordTextField").performTextInput(text)

        // Then
        composeTestRule
            .onNodeWithText(expected)
            .assertIsDisplayed()
    }

    @Test
    fun `카드_비밀번호는_숫자만_가능하다`() {
        // Given
        val text = "1aaa"
        val expected = "•"

        // When
        composeTestRule.onNodeWithTag("CardPasswordTextField").performTextInput(text)

        // Then
        composeTestRule
            .onNodeWithText(expected)
            .assertIsDisplayed()
    }
}
