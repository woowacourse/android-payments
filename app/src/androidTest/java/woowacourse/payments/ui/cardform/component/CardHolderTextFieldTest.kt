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
import woowacourse.payments.ui.cardform.components.CardHolderTextField

class CardHolderTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 카드_소유자_라벨을_출력한다() {
        // given & when
        composeRule.setContent { CardHolderTextField("", {}) }

        // then
        composeRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_힌트를_출력한다() {
        // given
        composeRule.setContent { CardHolderTextField("", {}) }

        // when
        composeRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performClick()

        // then
        composeRule
            .onNodeWithText("카드에 표시된 이름을 입력하세요.")
            .assertIsDisplayed()
    }

    @Test
    fun 문자와_숫자만_입력_가능하다() {
        // given
        var name by mutableStateOf("")
        composeRule.setContent {
            CardHolderTextField(value = name, onValueChange = { name = it })
        }
        val field = composeRule.onNode(hasText("카드 소유자 이름(선택)") and hasSetTextAction())

        // when
        field.performTextInput("공백")

        // then
        composeRule
            .onNodeWithText("공백")
            .assertIsDisplayed()

        // when
        field.performTextInput("0511")

        // then
        composeRule
            .onNodeWithText("공백0511")
            .assertIsDisplayed()
    }

    @Test
    fun 최대_30자까지_입력된다() {
        // given
        var name by mutableStateOf("")
        composeRule.setContent {
            CardHolderTextField(value = name, onValueChange = { name = it })
        }
        val field = composeRule.onNode(hasText("카드 소유자 이름(선택)") and hasSetTextAction())

        // when
        val long = "abcdeabcdeabcdeabcdeabcdeabcdeabcde"
        field.performTextInput(long)

        // then
        composeRule
            .onNodeWithText(long.take(30))
            .assertIsDisplayed()
    }

    @Test
    fun 입력_길이_카운터가_표시된다() {
        // given
        var name by mutableStateOf("")
        composeRule.setContent {
            CardHolderTextField(value = name, onValueChange = { name = it })
        }
        val field = composeRule.onNode(hasText("카드 소유자 이름(선택)") and hasSetTextAction())

        // when
        field.performTextInput("공공백백")

        // then
        composeRule
            .onNodeWithText("4/30")
            .assertIsDisplayed()
    }
}
