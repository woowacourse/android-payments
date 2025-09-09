package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.DigitTextField

class CardExpiryDateTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            DigitTextField(
                text = "",
                onValueChange = {},
                label = "만료일",
                hint = "MM / YY",
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
    fun 만료일은_4자를_초과할_수_없다() {
        composeTestRule
            .onNodeWithText("")
            .performTextInput("12345")

        composeTestRule
            .onNodeWithText("12/34")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일이_4자_미만일_때_4자여야_한다는_텍스트가_보인다() {
        // when
        composeTestRule
            .onNodeWithText("")
            .performTextInput("123")

        // then
        composeTestRule
            .onNodeWithText("유효하지 않은 만료일입니다.")
            .isDisplayed()
    }
}
