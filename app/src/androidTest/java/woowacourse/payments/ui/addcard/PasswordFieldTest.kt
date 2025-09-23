package woowacourse.payments.ui.addcard

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.model.PasswordUiModel

class PasswordFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupPasswordField(
        password: PasswordUiModel = Password.fromRawInput("").toUiModel(),
        onValueChange: (String) -> Unit = {},
    ) {
        composeTestRule.setContent {
            PasswordField(
                password = password,
                onValueChange = onValueChange,
            )
        }
    }

    @Test
    fun `onValueChange가_올바른_값으로_호출된다`() {
        // given
        var changedValue = ""
        setupPasswordField(onValueChange = { changedValue = it })
        val inputField = composeTestRule.onNodeWithText("비밀번호")

        // when
        val passwordInput = "1234"
        inputField.performTextInput(passwordInput)

        // then
        assert(changedValue == "1234")
    }
}
