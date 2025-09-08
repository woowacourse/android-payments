package woowacourse.payments.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardExpirationDateTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            var expirationDate by remember { mutableStateOf(CardExpirationDateUiModel("")) }
            CardExpirationDateTextField(
                cardExpirationDate = expirationDate,
                onCardExpirationDateChanged = { newValue -> expirationDate = newValue },
                modifier = Modifier.testTag(TEST_TAG),
            )
        }
    }

    @Test
    fun `카드_만료일은_숫자만_입력_가능하다`() {
        // when
        val textField = composeTestRule.onNodeWithTag(TEST_TAG)
        textField.performTextInput("1")
        textField.performTextInput("a")
        textField.performTextInput("2")

        // then
        composeTestRule
            .onNodeWithTag(TEST_TAG, useUnmergedTree = true)
            .assertTextEquals("12")
    }

    @Test
    fun `만료일의_월이_1-12_사이가_아닌_경우_에러_메시지가_보여진다`() {
        // when
        val textField = composeTestRule.onNodeWithTag(TEST_TAG)
        textField.performTextInput("1")
        textField.performTextInput("3")
        textField.performTextInput("2")
        textField.performTextInput("5")

        // then
        composeTestRule
            .onNodeWithText("유효하지 않은 만료일입니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일은_3자리가_될_때_슬래시_기호가_붙는다`() {
        // given
        val textField = composeTestRule.onNodeWithTag(TEST_TAG, useUnmergedTree = true)

        // when
        textField
            .performTextInput("123")

        // then
        textField.assertTextEquals("12 / 3")
    }

    @Test
    fun `입력한_값이_만료된_일자라면_에러_메시지가_보여진다`() {
        // given
        val textField = composeTestRule.onNodeWithTag(TEST_TAG, useUnmergedTree = true)

        // when
        textField.performTextInput("0924")

        // then
        composeTestRule
            .onNodeWithText("만료된 카드는 등록할 수 없습니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일_입력_값이_없는_경우_Placeholder가_보여진다`() {
        // given
        val textField = composeTestRule.onNodeWithTag(TEST_TAG, useUnmergedTree = true)

        // when
        textField.performClick()

        // then
        composeTestRule
            .onNodeWithText("MM / YY")
            .assertIsDisplayed()
    }

    companion object {
        private const val TEST_TAG = "CardExpirationDateTextField"
    }
}
