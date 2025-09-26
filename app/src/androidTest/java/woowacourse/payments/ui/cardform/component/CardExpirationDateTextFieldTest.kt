package woowacourse.payments.ui.cardform.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.CardExpirationDateUiModel

class CardExpirationDateTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setup() {
        composeTestRule.setContent {
            var expirationDate by remember { mutableStateOf("") }
            CardExpirationDateTextField(
                cardExpirationDate = CardExpirationDateUiModel(expirationDate),
                onCardExpirationDateChanged = { newValue -> expirationDate = newValue },
            )
        }
    }

    @Test
    fun `만료일의_월이_1-12_사이가_아닌_경우_예외가_발생한다`() {
        // given
        composeTestRule.setContent {
            var expirationDate by remember { mutableStateOf("") }
            val errorMessage = "유효하지 않은 만료일 입니다."
            CardExpirationDateTextField(
                cardExpirationDate = CardExpirationDateUiModel(expirationDate),
                onCardExpirationDateChanged = { newValue -> expirationDate = newValue },
                errorMessage = errorMessage,
            )
        }

        // when
        composeTestRule.onNodeWithContentDescription("만료일").run {
            performTextInput("1")
            performTextInput("3")
            performTextInput("2")
            performTextInput("5")
        }

        // then
        composeTestRule.onNodeWithText("유효하지 않은 만료일 입니다.").assertIsDisplayed()
    }

    @Test
    fun `만료일은_3자리가_될_때_슬래시_기호가_붙는다`() {
        // given
        setup()

        // when
        composeTestRule.onNodeWithContentDescription("만료일").performTextInput("123")

        // then
        composeTestRule
            .onNodeWithContentDescription("만료일", useUnmergedTree = true)
            .performTextInput("12 / 3")
    }

    @Test
    fun `만료일의_연도는_현재_연도_이후로_입력할_수_있다`() {
        // given
        setup()

        // when
        composeTestRule
            .onNodeWithContentDescription("만료일")
            .performTextInput("0925")

        // then
        composeTestRule
            .onNodeWithContentDescription("만료일", useUnmergedTree = true)
            .performTextInput("09 / 25")
    }

    @Test
    fun 만료일_입력_값이_없는_경우_입력창에_대한_설명이_보여진다() {
        // given
        setup()

        // when
        composeTestRule
            .onNodeWithContentDescription("만료일", useUnmergedTree = true)
            .performClick()
        // then
        composeTestRule
            .onNodeWithText("MM / YY")
            .assertIsDisplayed()
    }
}
