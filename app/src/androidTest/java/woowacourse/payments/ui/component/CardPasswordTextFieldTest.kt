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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertAll

class CardPasswordTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `입력과_포커스가_없으면_라벨만_보인다`() {
        // given
        setup()
        val textField = composeTestRule.onNodeWithTag(TEST_TAG)

        // then
        val labelText = "비밀번호"
        val editableText = ""

        assertAll(
            { textField.assertIsDisplayed() },
            { textField.assertIsNotFocused() },
            { textField.assertTextEquals(labelText, editableText) },
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
        val labelText = "비밀번호"
        val placeholderText = "0000"
        val editableText = ""

        assertAll(
            { textField.assertIsDisplayed() },
            { textField.assertIsFocused() },
            { textField.assertTextEquals(labelText, placeholderText, editableText) },
        )
    }

    @Test
    fun `오류_메시지가_제공되면_오류_텍스트가_보인다`() {
        // given
        setup(errorMessage = "형식이 올바르지 않습니다.")
        val textField = composeTestRule.onNodeWithTag(TEST_TAG)

        // then
        val labelText = "비밀번호"
        val editableText = ""
        val errorMessage = "형식이 올바르지 않습니다."

        assertAll(
            { textField.assertIsDisplayed() },
            { textField.assertTextEquals(labelText, editableText, errorMessage) },
        )
    }

    @Test
    fun `카드_비밀번호_입력이_있으면_입력된_비밀번호가_보인다`() {
        // given
        setup()
        val textField = composeTestRule.onNodeWithTag(TEST_TAG)

        // when
        textField.performTextInput("1234")

        // then
        val labelText = "비밀번호"
        val editableText = "1234"

        assertAll(
            { textField.assertIsDisplayed() },
            { textField.assertTextEquals(labelText, editableText) },
        )
    }

    @Test
    fun `입력이_있을_때_VisualTransformation을_적용하면_포맷팅된_비밀번호가_보인다`() {
        // given
        setup(visualTransformation = PasswordVisualTransformation())
        val textField = composeTestRule.onNodeWithTag(TEST_TAG)

        // when
        val testInput = "1234"
        textField.performTextInput(testInput)

        // then
        val labelText = "비밀번호"
        val editableText = "••••"

        assertAll(
            { textField.assertIsDisplayed() },
            { textField.assertTextEquals(labelText, editableText) },
        )
    }

    private fun setup(
        initialCardPassword: String = "",
        errorMessage: String? = null,
        visualTransformation: VisualTransformation = VisualTransformation.None,
    ) {
        composeTestRule.setContent {
            var cardPassword by remember { mutableStateOf(initialCardPassword) }
            CardPasswordTextField(
                cardPassword = cardPassword,
                onCardPasswordChanged = { newValue -> cardPassword = newValue },
                errorMessage = errorMessage,
                modifier = Modifier.testTag(TEST_TAG),
                visualTransformation = visualTransformation,
            )
        }
    }

    companion object {
        private const val TEST_TAG = "CardPasswordTextField"
    }
}
