package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardExpiryDateTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            DigitTextField(
                label = "만료일",
                hint = "MM / YY",
                fraction = 0.5f,
                maxLength = 4,
                mask = InputMask.Expiry,
                errorMessage = "유효하지 않은 만료일입니다.",
            )
        }
    }

    @Test
    fun 만료일은_4자이다() {
        composeTestRule
            .onNodeWithText("")
            .performTextInput("1234")

        composeTestRule
            .onNodeWithText("12/34")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_번호는_4자를_초과할_수_없다() {
        composeTestRule
            .onNodeWithText("")
            .performTextInput("12345")

        composeTestRule
            .onNodeWithText("12/34")
            .assertIsDisplayed()
    }
}
