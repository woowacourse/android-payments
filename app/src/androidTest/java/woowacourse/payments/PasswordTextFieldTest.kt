package woowacourse.payments

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.component.PinTextField

class PasswordTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setup() {
        composeRule.setContent {
            var pin by remember { mutableStateOf("") }

            PinTextField(
                value = pin,
                onValueChange = { pin = it },
                modifier =
                    Modifier
                        .fillMaxWidth(0.6f)
                        .testTag("password"),
            )
        }
    }

    @Test
    fun 비밀번호는_마스킹되어_출력된다() {
        // when
        composeRule
            .onNodeWithTag("password")
            .performTextInput("1")

        // then
        composeRule
            .onNodeWithText("•")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_숫자만_입력이_가능하다() {
        // when
        composeRule
            .onNodeWithTag("password")
            .performTextInput("19abcd")

        // then
        composeRule
            .onNodeWithText("••")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_4글자까지만_입력이_가능하다() {
        // when
        composeRule
            .onNodeWithTag("password")
            .performTextInput("19990511")

        // then
        composeRule
            .onNodeWithText("••••")
            .assertIsDisplayed()
    }
}
