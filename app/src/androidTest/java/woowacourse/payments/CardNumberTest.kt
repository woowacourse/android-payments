package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
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
                text = "",
                onValueChange = {},
                label = "카드 번호",
                hint = "0000 - 0000 - 0000 - 0000",
                mask = InputMask.CardNumber,
                errorMessage = "카드 번호는 16자여야 합니다.",
                maxLength = 16,
            )
        }
    }

    @Test
    fun 카드_번호는_16자이다() {
        // given
        val cardNumber = "1".repeat(16)

        // when
        composeTestRule
            .onNodeWithText("")
            .performTextInput(cardNumber)

        // then
        composeTestRule
            .onNodeWithText("1111-1111-1111-1111")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_번호는_16자를_초과할_수_없다() {
        val input = "1".repeat(17)

        composeTestRule
            .onNodeWithText("")
            .performTextInput(input)

        composeTestRule
            .onNodeWithText("1111-1111-1111-1111")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_번호가_16자_미만이면_16자여야_한다는_메시지가_보인다() {
        // given
        val input = "1".repeat(15)

        // when
        composeTestRule
            .onNodeWithText("")
            .performTextInput(input)

        // then
        composeTestRule
            .onNodeWithText("카드 번호는 16자여야 합니다.")
            .isDisplayed()
    }
}
