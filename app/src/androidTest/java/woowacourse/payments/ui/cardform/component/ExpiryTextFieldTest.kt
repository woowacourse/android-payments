@file:Suppress("ktlint:standard:function-naming")

package woowacourse.payments.ui.cardform.component

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
import woowacourse.payments.ui.cardform.components.ExpiryTextField

class ExpiryTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 만료일_라벨을_출력한다() {
        // given & when
        composeRule.setContent { ExpiryTextField("", {}) }

        // then
        composeRule.onNodeWithText("만료일").assertIsDisplayed()
    }

    @Test
    fun 만료일_힌트를_출력한다() {
        // given
        composeRule.setContent { ExpiryTextField("", {}) }

        // when
        composeRule.onNodeWithText("만료일").performClick()

        // then
        composeRule.onNodeWithText("MM / YY").assertIsDisplayed()
    }

    @Test
    fun 숫자만_입력_가능하다() {
        // given
        var expiry by mutableStateOf("")
        composeRule.setContent {
            ExpiryTextField(
                value = expiry,
                onValueChange = { expiry = it },
            )
        }
        val field = composeRule.onNode(hasText("만료일") and hasSetTextAction())

        // when
        field.performTextInput("1a2b3!")

        // then
        composeRule.onNodeWithText("12 / 3").assertIsDisplayed()
    }

    @Test
    fun 만료일은_4자까지_입력된다() {
        // given
        var expiry by mutableStateOf("")
        composeRule.setContent {
            ExpiryTextField(
                value = expiry,
                onValueChange = { expiry = it },
            )
        }
        val field = composeRule.onNode(hasText("만료일") and hasSetTextAction())

        // when
        field.performTextInput("1234")

        // then
        composeRule.onNodeWithText("12 / 34").assertIsDisplayed()
    }

    @Test
    fun 만료일은_4자를_초과하면_입력되지_않는다() {
        // given
        var expiry by mutableStateOf("")
        composeRule.setContent {
            ExpiryTextField(
                value = expiry,
                onValueChange = { expiry = it },
            )
        }
        val field = composeRule.onNode(hasText("만료일") and hasSetTextAction())

        // when
        field.performTextInput("12345")

        // then
        composeRule.onNodeWithText("12 / 34").assertIsDisplayed()
    }

    @Test
    fun 만료일은_입력시_자동으로_구분자가_포함된다() {
        // given
        var expiry by mutableStateOf("")
        composeRule.setContent {
            ExpiryTextField(
                value = expiry,
                onValueChange = { expiry = it },
            )
        }
        val field = composeRule.onNode(hasText("만료일") and hasSetTextAction())

        // when
        field.performTextInput("12")

        // then
        composeRule
            .onNodeWithText("12")
            .assertIsDisplayed()

        // when
        field.performTextInput("3")

        // then
        composeRule
            .onNodeWithText("12 / 3")
            .assertIsDisplayed()

        // when
        field.performTextInput("4")

        // then
        composeRule
            .onNodeWithText("12 / 34")
            .assertIsDisplayed()
    }
}
