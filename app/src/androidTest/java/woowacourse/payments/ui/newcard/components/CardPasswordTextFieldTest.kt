package woowacourse.payments.ui.newcard.components

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
class CardPasswordTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            var text by remember { mutableStateOf("") }
            CardPasswordTextField(value = text, onValueChange = { text = it }, isValid = true)
        }
    }

    @Test
    fun 카드_비밀번호_필드에_값을_입력하면_입력값이_표시된다() {
        // given
        val input = "0000"

        // when
        composeTestRule
            .onNodeWithText("카드 비밀번호")
            .performTextInput(input)

        // then
        composeTestRule
            .onNodeWithText(input)
            .assertIsDisplayed()
    }

    @Test
    fun 카드_비밀번호_필드에_최대_길이를_초과하여_값을_입력하면_제한된_길이까지_표시된다() {
        // given
        val input = "00000"

        // when
        composeTestRule
            .onNodeWithText("카드 비밀번호")
            .performTextInput(input)
        val expected = "0000"

        // then
        composeTestRule
            .onNodeWithText(expected)
            .assertIsDisplayed()
    }

    @Test
    fun 숫자가_아닌_값은_입력되지_않는다() {
        // given
        val input = "12a"

        // when
        composeTestRule
            .onNodeWithText("카드 비밀번호")
            .performTextInput(input)
        val expected = "12"

        // then
        composeTestRule
            .onNodeWithText(expected)
            .assertIsDisplayed()
    }

    @Test
    fun 카드_비밀번호는_마스킹되어_표시된다() {
        // given
        val input = "0000"

        // when
        composeTestRule
            .onNodeWithText("카드 비밀번호")
            .performTextInput(input)
        val expected = "••••"

        // then
        composeTestRule
            .onNodeWithText(expected)
            .assertIsDisplayed()
    }
}
