@file:Suppress("ktlint:standard:function-naming")

package woowacourse.payments.ui.newcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.newcard.components.CardNumberTextField

class CardNumberTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 카드_번호에_해당하는_라벨을_출력한다() {
        // given & when
        composeRule.setContent { CardNumberTextField("", {}) }

        // then
        composeRule
            .onNodeWithText("카드 번호")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_번호_힌트를_출력한다() {
        // given
        composeRule.setContent { CardNumberTextField("", {}) }

        // when
        composeRule
            .onNodeWithText("카드 번호")
            .performClick()

        // then
        composeRule
            .onNodeWithText("0000 – 0000 – 0000 – 0000")
            .assertIsDisplayed()
    }

    @Test
    fun 숫자만_입력_가능하다() {
        // given
        var cardNumber by mutableStateOf("")
        composeRule.setContent {
            CardNumberTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
            )
        }
        val field = composeRule.onNode(hasText("카드 번호") and hasSetTextAction())

        // when
        field
            .performTextInput("1a2b3c4d5!6@7#8$")

        // then
        composeRule
            .onNodeWithText("12345678")
            .assertIsDisplayed()
    }

    @Test
    fun 카드번호는_16자까지_입력된다() {
        // given
        var cardNumber by mutableStateOf("")
        composeRule.setContent {
            CardNumberTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
            )
        }
        val field = composeRule.onNode(hasText("카드 번호") and hasSetTextAction())

        // when
        field
            .performTextInput("1234123412341234")

        // then
        composeRule
            .onNodeWithText("1234123412341234")
            .assertIsDisplayed()
    }

    @Test
    fun 카드번호는_16자를_초과하면_입력되지_않는다() {
        // given
        var cardNumber by mutableStateOf("")
        composeRule.setContent {
            CardNumberTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
            )
        }
        val field = composeRule.onNode(hasText("카드 번호") and hasSetTextAction())

        // when
        field
            .performTextInput("12341234123412341")

        // then
        composeRule
            .onNodeWithText("1234123412341234")
            .assertIsDisplayed()
    }

    @Test
    fun 카드번호는_입력시_자동으로_구분자가_포함된다() {
        // given
        var cardNumber by mutableStateOf("")
        composeRule.setContent {
            CardNumberTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
            )
        }
        val field = composeRule.onNode(hasText("카드 번호") and hasSetTextAction())

        // when
        field
            .performTextInput("1234")

        // then
        composeRule
            .onNodeWithText("1234")
            .assertIsDisplayed()

        // when
        field
            .performTextInput("1")

        // then
        composeRule
            .onNodeWithText("1234 - 1")
            .assertIsDisplayed()

        // when
        field
            .performTextInput("2341")

        // then
        composeRule
            .onNodeWithText("1234 - 1234 - 1")
            .assertIsDisplayed()

        // when
        field
            .performTextInput("2341")

        // then
        composeRule
            .onNodeWithText("1234 - 1234 - 1234 - 1")
            .assertIsDisplayed()
    }
}
