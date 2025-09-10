package woowacourse.payments.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertAll
import woowacourse.payments.ui.model.CardNumberUiModel

class CardNumberTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            var cardNumber by remember { mutableStateOf(CardNumberUiModel("")) }
            CardNumberTextField(
                cardNumber = cardNumber,
                onCardNumberChanged = { newValue -> cardNumber = newValue },
                modifier = Modifier.testTag(TEST_TAG),
            )
        }
    }

    @Test
    fun `카드_번호는_숫자만_입력_가능해야_한다`() {
        // given
        val textField = composeTestRule.onNodeWithTag(TEST_TAG)

        // when
        textField.performTextInput("1")
        textField.performTextInput("a")
        textField.performTextInput("2")

        // then
        composeTestRule
            .onNodeWithTag(TEST_TAG, useUnmergedTree = true)
            .assertTextEquals("12")
    }

    @Test
    fun `카드_번호는_길이가_16자를_넘어갈_수_없다`() {
        // given
        val textField =
            composeTestRule.onNodeWithTag(TEST_TAG, useUnmergedTree = true)

        // when
        repeat(17) { textField.performTextInput("1") }

        // then
        textField.assertTextEquals("1111 - 1111 - 1111 - 1111")
    }

    @Test
    fun `카드_번호는_4자리가_될_때_마다_대시_기호가_붙는다`() {
        // given
        val textField =
            composeTestRule.onNodeWithTag(TEST_TAG, useUnmergedTree = true)
        val testCases =
            listOf(
                "1111" to "1111",
                "11111" to "1111 - 1",
                "123456789" to "1234 - 5678 - 9",
                "1234567890123" to "1234 - 5678 - 9012 - 3",
                "1234567890123456" to "1234 - 5678 - 9012 - 3456",
            )

        assertAll(
            "카드 번호 포맷 테스트",
            testCases.map { (input, expected) ->
                {
                    // when
                    textField.performTextInput(input)

                    // then
                    textField.assertTextEquals(expected)
                    textField.performTextClearance()
                }
            },
        )
    }

    companion object {
        private const val TEST_TAG = "CardNumberTextField"
    }
}
