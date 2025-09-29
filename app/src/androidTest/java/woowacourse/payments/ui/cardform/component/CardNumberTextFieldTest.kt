package woowacourse.payments.ui.cardform.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.CardNumberUiModel

class CardNumberTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            var cardNumber by remember { mutableStateOf("") }
            CardNumberTextField(
                cardNumber = CardNumberUiModel(cardNumber),
                onCardNumberChanged = { newValue ->
                    cardNumber = newValue
                },
            )
        }
    }

    @Test
    fun `카드_번호는_4자리가_될_때_마다_대시_기호가_붙는다`() {
        val csvSource =
            arrayOf(
                "1111,1111",
                "11111,1111 - 1",
                "123456789,1234 - 5678 - 9",
                "1234567890123,1234 - 5678 - 9012 - 3",
                "1234567890123456,1234 - 5678 - 9012 - 3456",
            )

        csvSource.forEach { csv ->
            val (input, expected) = csv.split(",")

            // when
            composeTestRule
                .onNodeWithContentDescription("카드 번호")
                .performTextInput(input)

            // then
            composeTestRule
                .onNodeWithContentDescription("카드 번호", useUnmergedTree = true)
                .assertTextEquals(expected)

            composeTestRule
                .onNodeWithContentDescription("카드 번호")
                .performTextClearance()
        }
    }
}
