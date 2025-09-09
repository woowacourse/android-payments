@file:Suppress("ktlint:standard:function_naming")

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
import woowacourse.payments.ui.newcard.components.PinTextField

class PinTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 비밀번호_라벨을_출력한다() {
        // given & when
        composeRule.setContent { PinTextField("", {}) }

        // then
        composeRule
            .onNodeWithText("비밀번호")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호_힌트를_출력한다() {
        // given
        composeRule.setContent { PinTextField("", {}) }

        // when
        composeRule
            .onNodeWithText("비밀번호")
            .performClick()

        // then
        composeRule
            .onNodeWithText("0000")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_숫자만_입력_가능하다() {
        // given
        var pin by mutableStateOf("")
        composeRule.setContent {
            PinTextField(
                value = pin,
                onValueChange = { pin = it },
            )
        }
        val field = composeRule.onNode(hasText("비밀번호") and hasSetTextAction())

        // when
        field.performTextInput("1a2!3")

        // then
        composeRule
            .onNodeWithText("123")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_4자까지_입력된다() {
        // given
        var pin by mutableStateOf("")
        composeRule.setContent {
            PinTextField(
                value = pin,
                onValueChange = { pin = it },
            )
        }
        val field = composeRule.onNode(hasText("비밀번호") and hasSetTextAction())

        // when
        field.performTextInput("12345")

        // then
        composeRule
            .onNodeWithText("1234")
            .assertIsDisplayed()
    }

    @Test
    fun 입력시_마스킹한_비밀번호를_출력한다() {
        // given
        var pin by mutableStateOf("")
        composeRule.setContent {
            PinTextField(
                value = pin,
                onValueChange = { pin = it },
            )
        }
        val field = composeRule.onNode(hasText("비밀번호") and hasSetTextAction())

        // when
        field.performTextInput("1234")

        // then
        composeRule.onNodeWithText("••••").assertIsDisplayed()
    }
}
