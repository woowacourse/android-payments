package woowacourse.payments.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.payments.CardExpirationDateTextField

class CardExpirationDateTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setup() {
        composeTestRule.setContent {
            var expirationDate by remember { mutableStateOf("") }
            CardExpirationDateTextField(
                cardExpirationDate = expirationDate,
                onCardExpirationDateChanged = { newValue -> expirationDate = newValue },
                onErrorMessageChanged = {},
            )
        }
    }

    @Test
    fun `카드_만료일은_숫자만_입력_가능하다`() {
        // given
        setup()

        // when
        val textField = composeTestRule.onNodeWithTag("CardExpirationDateTextField")
        textField.performTextInput("1")
        textField.performTextInput("a")
        textField.performTextInput("2")

        // then
        composeTestRule
            .onNodeWithTag("CardExpirationDateTextField", useUnmergedTree = true)
            .assertTextEquals("12")
    }

    @Test
    fun `만료일의_월이_1-12_사이가_아닌_경우_예외가_발생한다`() {
        // given
        composeTestRule.setContent {
            var expirationDate by remember { mutableStateOf("") }
            var errorMessage by remember { mutableStateOf("") }
            CardExpirationDateTextField(
                cardExpirationDate = expirationDate,
                onCardExpirationDateChanged = { newValue -> expirationDate = newValue },
                errorMessage = errorMessage,
                onErrorMessageChanged = { newValue -> errorMessage = newValue.orEmpty() },
            )
        }

        // when
        val textField = composeTestRule.onNodeWithTag("CardExpirationDateTextField")
        textField.performTextInput("1")
        textField.performTextInput("3")
        textField.performTextInput("2")
        textField.performTextInput("5")

        // then
        composeTestRule
            .onNodeWithText("유효하지 않은 만료일 입니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일은_3자리가_될_때_슬래시_기호가_붙는다`() {
        // given
        setup()

        // when
        composeTestRule
            .onNodeWithTag("CardExpirationDateTextField")
            .performTextInput("123")

        // then
        composeTestRule
            .onNodeWithTag("CardExpirationDateTextField", useUnmergedTree = true)
            .assertTextEquals("12 / 3")
    }

    @Test
    fun `만료일의_연도는_현재_연도_이후로_입력할_수_있다`() {
        // given
        setup()

        // when
        composeTestRule
            .onNodeWithTag("CardExpirationDateTextField")
            .performTextInput("0925")

        // then
        composeTestRule
            .onNodeWithTag("CardExpirationDateTextField", useUnmergedTree = true)
            .assertTextEquals("09 / 25")
    }

    @Test
    fun `만료일_입력_값이_없는_경우_Placeholder가_보여진다`() {
        // given
        setup()

        // when
        composeTestRule
            .onNodeWithTag("CardExpirationDateTextField", useUnmergedTree = true)
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("MM / YY")
            .assertIsDisplayed()
    }
}
