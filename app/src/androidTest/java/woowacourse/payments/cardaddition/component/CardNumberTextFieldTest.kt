package woowacourse.payments.cardaddition.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardNumberTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            val (cardNumber: String, setCardNumber: (String) -> Unit) = remember { mutableStateOf("") }

            CardNumberTextField(
                value = cardNumber,
                onValueChange = setCardNumber,
                isError = false,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }

    @Test
    fun `카드_번호의_경우_입력할_때_자동으로_기호가_삽입된다`() {
        // when
        composeRule
            .onNodeWithText("")
            .performTextInput("1234567812345678")

        // then
        composeRule
            .onNodeWithText("1234 - 5678 - 1234 - 5678")
            .assertIsDisplayed()
    }
}
