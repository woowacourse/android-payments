package woowacourse.payments.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardNumberTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            var value by remember { mutableStateOf("") }
            CardNumberTextField(
                value = value,
                onCardNumberChange = { value = it },
                modifier = Modifier.testTag("CardNumberTextFieldTest")
            )
        }
    }

    @Test
    fun 카드_번호는_16자이다() {
        // when
        composeRule
            .onNodeWithTag("CardNumberTextFieldTest")
            .performTextInput("12345678123456781")

        // then
        composeRule
            .onNodeWithTag("CardNumberTextFieldTest")
            .assert(hasText("1234 - 5678 - 1234 - 5678"))
    }

    @Test
    fun `숫자가_아닌_값을_입력할_수_없다`() {
        // when
        composeRule
            .onNodeWithTag("CardNumberTextFieldTest")
            .performTextInput("123NaN")

        // then
        composeRule
            .onNodeWithTag("CardNumberTextFieldTest")
            .assert(hasText("123"))
    }

    @Test
    fun `숫자_4자리마다_구분자가_들어간다`() {
        // when
        composeRule
            .onNodeWithTag("CardNumberTextFieldTest")
            .performTextInput("1234567812345678")

        // then
        composeRule
            .onNodeWithTag("CardNumberTextFieldTest")
            .assert(hasText("1234 - 5678 - 1234 - 5678"))
    }
}
