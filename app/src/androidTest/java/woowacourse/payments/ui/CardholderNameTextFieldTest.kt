package woowacourse.payments.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import woowacourse.payments.ui.payments.CardholderNameTextField

class CardholderNameTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            var cardholderName by remember { mutableStateOf("") }
            CardholderNameTextField(
                cardholderName = cardholderName,
                onCardholderNameChanged = { newValue -> cardholderName = newValue },
            )
        }
    }

    @Test
    fun `카드_소유자_이름은_영문만_입력할_수_있다`() {
        // when
        val textField =
            composeTestRule.onNodeWithTag("CardholderNameTextField", useUnmergedTree = true)

        textField.performTextInput("C")
        textField.performTextInput("1")
        textField.performTextInput("R")
        textField.performTextInput("E")
        textField.performTextInput("w")

        // then
        composeTestRule
            .onNodeWithTag("CardholderNameTextField", useUnmergedTree = true)
            .assertTextEquals("CREW")
    }

    @Test
    fun `카드_소유자_이름의_최대_글자는_30자이다`() {
        // when
        composeTestRule
            .onNodeWithTag("CardholderNameTextField", useUnmergedTree = true)
            .performTextInput("C".repeat(31))

        // then
        composeTestRule
            .onNodeWithTag("CardholderNameTextField", useUnmergedTree = true)
            .assertTextEquals("C".repeat(30))
    }

    @Test
    fun `카드_소유자_이름의_길이가_표시된다`() {
        // when
        composeTestRule
            .onNodeWithTag("CardholderNameTextField", useUnmergedTree = true)
            .performTextInput("ABCDE")

        // then
        composeTestRule
            .onNodeWithText("5/30")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일_입력_값이_없는_경우_Placeholder가_보여진다`() {
        // when
        composeTestRule
            .onNodeWithTag("CardholderNameTextField", useUnmergedTree = true)
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("카드에 표시된 이름을 입력하세요.")
            .assertIsDisplayed()
    }
}
