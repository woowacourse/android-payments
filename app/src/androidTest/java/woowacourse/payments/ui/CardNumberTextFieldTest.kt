package woowacourse.payments.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.payments.CardNumberTextField

class CardNumberTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            var state by remember { mutableStateOf("") }
            CardNumberTextField(cardNumber = state) { newValue ->
                state = newValue
            }
        }
    }

    @Test
    fun `카드_번호는_숫자만_입력_가능해야_한다`() {
        // when
        val textField = composeTestRule.onNodeWithTag("CardNumberTextField")
        textField.performTextInput("1")
        textField.performTextInput("a")
        textField.performTextInput("2")

        // then
        composeTestRule
            .onNodeWithTag("CardNumberTextField", useUnmergedTree = true)
            .assertTextEquals("12")
    }

    @Test
    fun `카드_번호는_길이가_16자를_넘어갈_수_없다`() {
        // when
        composeTestRule.onNodeWithTag("CardNumberTextField")
            .performTextInput("1".repeat(17))

        // then
        composeTestRule
            .onNodeWithTag("CardNumberTextField", useUnmergedTree = true)
            .assertTextEquals("1111 - 1111 - 1111 - 1111")
    }

    @Test
    fun `카드_번호는_4자리가_될_때_마다_대시_기호가_붙는다`() {
        val csvSource = arrayOf(
            "1111,1111",
            "11111,1111 - 1",
            "123456789,1234 - 5678 - 9",
            "1234567890123,1234 - 5678 - 9012 - 3",
            "1234567890123456,1234 - 5678 - 9012 - 3456"
        )

        csvSource.forEach { csv ->
            val (input, expected) = csv.split(",")
            Log.e("TAG", "$input, $expected")

            // when
            composeTestRule.onNodeWithTag("CardNumberTextField")
                .performTextInput(input)

            // then
            composeTestRule.onNodeWithTag("CardNumberTextField", useUnmergedTree = true)
                .assertTextEquals(expected)

            composeTestRule.onNodeWithTag("CardNumberTextField")
                .performTextClearance()
        }
    }
}
