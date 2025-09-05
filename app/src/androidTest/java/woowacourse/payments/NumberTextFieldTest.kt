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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.component.NumberTextField
import woowacourse.payments.ui.transformation.NumberVisualTransformation

class NumberTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    // -------------------------
    // 카드번호
    // -------------------------
    @Test
    fun 카드번호는_16자리_숫자까지만_입력이_가능하고_4자리마다_구분된다() {
        composeRule.setContent {
            var cardNumber by remember { mutableStateOf("") }
            NumberTextField(
                modifier = Modifier.testTag("card_number"),
                label = R.string.label_card_number,
                placeholder = R.string.placeholder_card_number,
                value = cardNumber,
                onValueChange = { cardNumber = it },
                maxLength = 16,
                visualTransformation = NumberVisualTransformation(4, " - "),
            )
        }

        // when
        composeRule
            .onNodeWithTag("card_number")
            .performTextInput("200109281999051112345678")

        // then
        composeRule
            .onNodeWithText("2001 - 0928 - 1999 - 0511")
            .assertIsDisplayed()
    }

    // -------------------------
    // 만료일
    // -------------------------
    @Test
    fun 만료일은_MM_슬래시_YY_형태로_자동_구분된다() {
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

        // when
        composeRule
            .onNodeWithTag("expiry")
            .performTextInput("0511")

        // then
        composeRule
            .onNodeWithText("05 / 11")
            .assertIsDisplayed()
    }

    // -------------------------
    // 비밀번호
    // -------------------------
    @Test
    fun 비밀번호는_4글자까지만_입력되고_시각적으로_가려진다() {
        composeRule.setContent {
            var pin by remember { mutableStateOf("") }
            NumberTextField(
                modifier = Modifier.testTag("password"),
                label = R.string.label_pin,
                placeholder = R.string.placeholder_pin,
                value = pin,
                onValueChange = { pin = it },
                maxLength = 4,
                visualTransformation = PasswordVisualTransformation(),
            )
        }

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
