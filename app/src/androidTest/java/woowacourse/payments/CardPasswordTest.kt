package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.dp
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardPasswordTest {
    private val masking = '\u2022'.toString()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            DigitFieldText(label = "비밀번호",
                hint = "0000",
                fraction = 0.5f,
                maxLength = 4,
                mask = InputMask.Password,
                height = 10.dp,
                errorMessage = "비밀번호는 4자입니다."
            )
        }
    }

    @Test
    fun 비밀번호는_4자이다() {


        composeTestRule
            .onNodeWithText("")
            .performTextInput("1234")

        composeTestRule
            .onNodeWithText(masking.repeat(4))
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_4자를_초과할_수_없다() {
        composeTestRule
            .onNodeWithText("")
            .performTextInput("1234")

        composeTestRule
            .onNodeWithText(masking.repeat(4))
            .assertIsDisplayed()
    }
}
