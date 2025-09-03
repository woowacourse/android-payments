package woowacourse.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.CardNumberTextField

class CardNumberTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            CardNumberTextField()
        }
    }

    @Test
    fun `숫자가_아닌_값을_입력할_수_없다`() {
        // when
        composeRule.onNodeWithText("")
            .performTextInput("123NaN")

        // then
        composeRule.onNodeWithText("123")
            .assertIsDisplayed()
    }

    @Test
    fun `숫자_4자리마다_구분자가_들어간다`() {
        // when
        composeRule.onNodeWithText("")
            .performTextInput("1234567812345678")

        // then
        composeRule.onNodeWithText("1234-5678-1234-5678")
            .assertIsDisplayed()
    }
}