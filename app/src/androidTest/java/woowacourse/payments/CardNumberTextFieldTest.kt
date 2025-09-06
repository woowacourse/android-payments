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

class CardNumberTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setup() {
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
    }

    @Test
    fun 카드번호는_숫자만_입력이_가능하다() {
        // when
        composeRule
            .onNodeWithTag("card_number")
            .performTextInput("2001ab09")

        // then
        composeRule
            .onNodeWithText("2001 - 09")
            .assertIsDisplayed()
    }

    @Test
    fun 카드번호는_대시로_구분되어_출력된다() {
        // when
        composeRule
            .onNodeWithTag("card_number")
            .performTextInput("20010928")

        // then
        composeRule
            .onNodeWithText("2001 - 0928")
            .assertIsDisplayed()
    }

    @Test
    fun 카드번호는_16자리까지만_입력이_가능하다() {
        // when
        composeRule
            .onNodeWithTag("card_number")
            .performTextInput("200109281999051112345678")

        // then
        composeRule
            .onNodeWithText("2001 - 0928 - 1999 - 0511")
            .assertIsDisplayed()
    }
}
