package woowacourse.payments

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
import woowacourse.payments.ui.component.NumberTextField
import woowacourse.payments.ui.transformation.NumberVisualTransformation

class ExpiryTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setup() {
        composeRule.setContent {
            var expiry by remember { mutableStateOf("") }
            NumberTextField(
                modifier = Modifier.testTag("expiry"),
                label = R.string.label_expiry,
                placeholder = R.string.placeholder_expiry,
                value = expiry,
                onValueChange = { expiry = it },
                maxLength = 4,
                visualTransformation = NumberVisualTransformation(2, " / "),
            )
        }
    }

    @Test
    fun 만료일은_숫자만_입력이_가능하다() {
        // when
        composeRule
            .onNodeWithTag("expiry")
            .performTextInput("05abcd")

        // then
        composeRule
            .onNodeWithText("05")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일은_슬래시로_구분되어_출력된다() {
        // when
        composeRule
            .onNodeWithTag("expiry")
            .performTextInput("051")

        // then
        composeRule
            .onNodeWithText("05 / 1")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일은_4자까지만_입력이_가능하다() {
        // when
        composeRule
            .onNodeWithTag("expiry")
            .performTextInput("05119876")

        // then
        composeRule
            .onNodeWithText("05 / 11")
            .assertIsDisplayed()
    }
}
