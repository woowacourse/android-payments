package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class DigitFieldTextTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드_번호는_16자이다() {
        composeTestRule.setContent {
            DigitFieldText(
                label = "카드 번호",
                hint = "0000 - 0000 - 0000 - 0000",
                mask = InputMask.CardNumber,
                errorMessage = "카드 번호는 16자여야 합니다."
            )
        }

        composeTestRule
            .onNodeWithText("") // TextField 초기 상태
            .performTextInput("1234123412341234")

        composeTestRule
            .onNodeWithText("1234-1234-1234-1234")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_번호는_16자를_초과할_수_없다() {
        composeTestRule.setContent {
            DigitFieldText(
                label = "카드 번호",
                hint = "0000 - 0000 - 0000 - 0000",
                mask = InputMask.CardNumber,
                errorMessage = "카드 번호는 16자여야 합니다."
            )
        }

        composeTestRule
            .onNodeWithText("")
            .performTextInput("12341234123412341") // 17자리 입력

        composeTestRule
            .onNodeWithText("1234-1234-1234-1234") // 16자리까지만 표시
            .assertIsDisplayed()
    }
}
