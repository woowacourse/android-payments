package woowacourse.payments

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.component.CardPassword
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardPasswordTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 비밀번호는_최대_4자리까지만_입력된다() {
        var passwordInput = ""
        composeTestRule.setContent {
            AndroidpaymentsTheme {
                CardPassword(
                    value = passwordInput,
                    onValueChange = { passwordInput = it }
                )
            }
        }
        composeTestRule.onNodeWithText("비밀번호").performTextInput("12345")
    }
}
