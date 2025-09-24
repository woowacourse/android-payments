package woowacourse.payments.ui.cardupdate.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("ktlint:standard:function-naming")
class CardHolderNameTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            var text by remember { mutableStateOf("") }
            CardHolderNameTextField(value = text, onValueChange = { text = it }, isValid = true)
        }
    }

    @Test
    fun 카드_소유자_이름_필드에_값을_입력하면_입력값이_표시된다() {
        // given
        val input = "A".repeat(30)

        // when
        composeTestRule
            .onNodeWithText("카드 소유자 이름 (선택)")
            .performTextInput(input)

        // then
        composeTestRule
            .onNodeWithText(input)
            .assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_이름_필드에_최대_길이를_초과하여_값을_입력하면_제한된_길이까지_표시된다() {
        // given
        val input = "A".repeat(50)

        // when
        composeTestRule
            .onNodeWithText("카드 소유자 이름 (선택)")
            .performTextInput(input)
        val expected = input.take(30)

        // then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }

    @Test
    fun 영문_대문자가_아닌_값은_입력되지_않는다() {
        // given
        val input = "ABC123"

        // when
        composeTestRule
            .onNodeWithText("카드 소유자 이름 (선택)")
            .performTextInput(input)
        val expected = "ABC"

        // then
        composeTestRule
            .onNodeWithText(expected)
            .assertIsDisplayed()
    }
}
