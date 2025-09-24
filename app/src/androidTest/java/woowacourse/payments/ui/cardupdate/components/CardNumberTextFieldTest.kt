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
class CardNumberTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            var text by remember { mutableStateOf("") }
            CardNumberTextField(value = text, onValueChange = { text = it }, isValid = true)
        }
    }

    @Test
    fun 카드_번호_필드에_값을_입력하면_자동으로_기호가_삽입된다() {
        // given
        val input = "1234".repeat(4)

        // when
        composeTestRule
            .onNodeWithText("카드 번호")
            .performTextInput(input)
        val expected = "1234 - 1234 - 1234 - 1234"

        // then
        composeTestRule
            .onNodeWithText(expected)
            .assertIsDisplayed()
    }

    @Test
    fun 카드_번호_필드에_최대_길이를_초과하여_값을_입력하면_제한된_길이까지_표시된다() {
        // given
        val input = "1234".repeat(5)

        // when
        composeTestRule
            .onNodeWithText("카드 번호")
            .performTextInput(input)
        val expected = "1234 - 1234 - 1234 - 1234"

        // then
        composeTestRule
            .onNodeWithText(expected)
            .assertIsDisplayed()
    }

    @Test
    fun 숫자가_아닌_값은_입력되지_않는다() {
        // given
        val input = "123a"

        // when
        composeTestRule
            .onNodeWithText("카드 번호")
            .performTextInput(input)
        val expected = "123"

        // then
        composeTestRule
            .onNodeWithText(expected)
            .assertIsDisplayed()
    }
}
