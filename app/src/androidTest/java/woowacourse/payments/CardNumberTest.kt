package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardNumberTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            DigitTextField(
                label = "카드 번호",
                hint = "0000 - 0000 - 0000 - 0000",
                mask = InputMask.CardNumber,
                errorMessage = "카드 번호는 16자여야 합니다.",
            )
        }
    }

    @Test
    fun 카드_번호는_16자이다() {
        composeTestRule
            .onNodeWithText("")
            .performTextInput("1234123412341234")

        composeTestRule
            .onNodeWithText("1234-1234-1234-1234")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_번호는_16자를_초과할_수_없다() {
        composeTestRule
            .onNodeWithText("")
            .performTextInput("12341234123412341")

        composeTestRule
            .onNodeWithText("1234-1234-1234-1234")
            .assertIsDisplayed()
    }
}
