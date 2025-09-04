package woowacourse.payments

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import woowacourse.payments.component.NumberTextField

@RunWith(AndroidJUnit4::class)
class NumberTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    // -------------------------
    // Card Number
    // -------------------------
    @Test
    fun 카드번호는_16자리_숫자까지만_입력이_가능하다() {
        composeRule.setContent {
            NumberTextField(
                modifier = Modifier.testTag("card_number"),
                label = R.string.label_card_number,
                placeholder = R.string.placeholder_card_number,
                inputType = InputType.CardNumber
            )
        }

        // when
        composeRule.onNodeWithTag("card_number")
            .performTextInput("200109281999051112345678")

        // then
        composeRule.onNodeWithText("2001 - 0928 - 1999 - 0511")
            .assertIsDisplayed()
    }

    @Test
    fun 카드번호를_입력하면_구분자로_구분된다() {
        composeRule.setContent {
            NumberTextField(
                modifier = Modifier.testTag("card_number"),
                label = R.string.label_card_number,
                placeholder = R.string.placeholder_card_number,
                inputType = InputType.CardNumber
            )
        }

        // when
        composeRule.onNodeWithTag("card_number")
            .performTextInput("2001092819990511")

        // then
        composeRule.onNodeWithText("2001 - 0928 - 1999 - 0511")
            .assertIsDisplayed()
    }

    // -------------------------
    // Expiry Date
    // -------------------------
    @Test
    fun 만료일을_입력하면_구분자로_구분된다() {
        composeRule.setContent {
            NumberTextField(
                modifier = Modifier.testTag("expiry"),
                label = R.string.label_expiry,
                placeholder = R.string.placeholder_expiry,
                inputType = InputType.ExpiryDate
            )
        }

        // when
        composeRule.onNodeWithTag("expiry")
            .performTextInput("0511")

        // then
        composeRule.onNodeWithText("05 / 11")
            .assertIsDisplayed()
    }

    // -------------------------
    // Password
    // -------------------------
    @Test
    fun 입력된_비밀번호는_노출되지_않는다() {
        composeRule.setContent {
            NumberTextField(
                modifier = Modifier.testTag("password"),
                label = R.string.label_pin,
                placeholder = R.string.placeholder_pin,
                inputType = InputType.Password
            )
        }

        // when
        composeRule.onNodeWithTag("password")
            .performTextInput("12")

        // then (보이는 텍스트 기준)
        composeRule.onNodeWithText("••")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_4글자까지만_입력된다() {
        composeRule.setContent {
            NumberTextField(
                modifier = Modifier.testTag("password"),
                label = R.string.label_pin,
                placeholder = R.string.placeholder_pin,
                inputType = InputType.Password
            )
        }

        // when
        composeRule.onNodeWithTag("password")
            .performTextInput("19990511")

        // then
        composeRule.onNodeWithText("••••")
            .assertIsDisplayed()
    }
}