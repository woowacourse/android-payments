package woowacourse.payments.component

import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performTextInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Rule
import org.junit.Test

class CardPasswordEditTextTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `비밀번호는_4글자까지_입력할_수_있다`() {
        // given
        var password = "1234"
        composeTestRule.setContent {
            CardPasswordTextField(
                maxLength = 4,
                password = password,
                onPasswordChange = { password = it }
            )
        }

        // when
        composeTestRule.onNode(hasSetTextAction()).performTextInput("5")

        // then
        assertEquals(password, "1234")
    }
}
