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
                modifier = Modifier.testTag(CARDHOLDER_NAME_TEXT_FIELD_TEST_TAG),
            )
        }
    }

    @Test
    fun `카드_소유자_이름은_영문만_입력할_수_있다`() {
        // when
        val textField =
            composeTestRule.onNodeWithTag(
                CARDHOLDER_NAME_TEXT_FIELD_TEST_TAG,
                useUnmergedTree = true,
            )

        textField.performTextInput("C")
        textField.performTextInput("1")
        textField.performTextInput("R")
        textField.performTextInput("E")
        textField.performTextInput("w")

        // then
        textField.assertTextEquals("CREW")
    }

    @Test
    fun `카드_소유자_이름의_최대_글자는_30자이다`() {
        // given
        val textField =
            composeTestRule
                .onNodeWithTag(CARDHOLDER_NAME_TEXT_FIELD_TEST_TAG, useUnmergedTree = true)

        // when
        textField.performTextInput("C".repeat(30))
        textField.performTextInput("C")

        // then
        textField.assertTextEquals("C".repeat(30))
    }

    @Test
    fun `카드_소유자_이름의_길이가_표시된다`() {
        // when
        composeTestRule
            .onNodeWithTag(CARDHOLDER_NAME_TEXT_FIELD_TEST_TAG, useUnmergedTree = true)
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
            .onNodeWithTag(CARDHOLDER_NAME_TEXT_FIELD_TEST_TAG, useUnmergedTree = true)
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("카드에 표시된 이름을 입력하세요.")
            .assertIsDisplayed()
    }

    companion object {
        private const val CARDHOLDER_NAME_TEXT_FIELD_TEST_TAG = "CardholderNameTextField"
    }
}
