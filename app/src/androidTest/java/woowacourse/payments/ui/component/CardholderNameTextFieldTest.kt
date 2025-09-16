package woowacourse.payments.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.requestFocus
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertAll

class CardholderNameTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `입력과_포커스가_없으면_라벨만_보인다`() {
        // given
        setup()
        val textField = composeTestRule.onNodeWithTag(TEST_TAG)

        // then
        val labelText = "카드 소유자 이름(선택)"
        val supportingText = "0/30"
        val editableText = ""

        assertAll(
            { textField.assertIsDisplayed() },
            { textField.assertIsNotFocused() },
            { textField.assertTextEquals(labelText, supportingText, editableText) },
        )
    }

    @Test
    fun `입력이_없고_포커스가_있으면_라벨과_플레이스홀더가_보인다`() {
        // given
        setup()
        val textField = composeTestRule.onNodeWithTag(TEST_TAG)

        // when
        textField.requestFocus()

        // then
        val labelText = "카드 소유자 이름(선택)"
        val placeholderText = "카드에 표시된 이름을 입력하세요."
        val supportingText = "0/30"
        val editableText = ""

        assertAll(
            { textField.assertIsDisplayed() },
            { textField.assertIsFocused() },
            {
                textField.assertTextEquals(
                    labelText,
                    supportingText,
                    placeholderText,
                    editableText,
                )
            },
        )
    }

    @Test
    fun `최대_글자_이상으로는_입력이_되지_않는다`() {
        // given
        val maxLength = 10
        val initialCardholderName = "A".repeat(maxLength)
        setup(initialCardholderName, maxLength)
        val textField = composeTestRule.onNodeWithTag(TEST_TAG)

        // when
        textField.performTextInput("B")

        // then
        val labelText = "카드 소유자 이름(선택)"
        val supportingText = "10/10"
        val editableText = "A".repeat(maxLength)

        assertAll(
            { textField.assertIsDisplayed() },
            { textField.assertTextEquals(labelText, supportingText, editableText) },
        )
    }

    @Test
    fun `입력_문자열의_길이가_표시된다`() {
        // given
        setup(maxLength = 30)
        val textField = composeTestRule.onNodeWithTag(TEST_TAG)

        // when
        textField.performTextInput("ABCDE")

        // then
        val labelText = "카드 소유자 이름(선택)"
        val supportingText = "5/30"
        val editableText = "ABCDE"

        assertAll(
            { textField.assertIsDisplayed() },
            { textField.assertTextEquals(labelText, supportingText, editableText) },
        )
    }

    @Test
    fun `오류_메시지가_제공되면_오류_텍스트가_보인다`() {
        // given
        setup(errorMessage = "형식이 올바르지 않습니다.")
        val textField = composeTestRule.onNodeWithTag(TEST_TAG)

        // then
        val labelText = "카드 소유자 이름(선택)"
        val supportingText = "0/30"
        val editableText = ""
        val errorMessage = "형식이 올바르지 않습니다."

        assertAll(
            { textField.assertIsDisplayed() },
            { textField.assertTextEquals(labelText, supportingText, editableText, errorMessage) },
        )
    }

    private fun setup(
        initialCardholderName: String = "",
        maxLength: Int = 30,
        errorMessage: String? = null,
    ) {
        composeTestRule.setContent {
            var cardholderName by remember { mutableStateOf(initialCardholderName) }
            CardholderNameTextField(
                cardholderName = cardholderName,
                onCardholderNameChanged = { newValue -> cardholderName = newValue },
                maxLength = maxLength,
                errorMessage = errorMessage,
                modifier = Modifier.testTag(TEST_TAG),
            )
        }
    }

    companion object {
        private const val TEST_TAG = "CardholderNameTextField"
    }
}
