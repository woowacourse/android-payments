package woowacourse.payments.ui.addcard

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.CardNumberUiModel

class CardNumberFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupCardNumberField(
        cardNumber: CardNumberUiModel = CardNumberUiModel("", "", "", ""),
        onValueChange: (String) -> Unit = {},
    ) {
        composeTestRule.setContent {
            CardNumberField(
                cardNumber = cardNumber,
                onValueChange = onValueChange,
            )
        }
    }

    @Test
    fun `onValueChange가_올바른_값으로_호출된다`() {
        // given
        var changedValue = ""
        setupCardNumberField(onValueChange = { changedValue = it })
        val inputField = composeTestRule.onNodeWithText("카드 번호")

        // when
        val cardNumberInput = "1234"
        inputField.performTextInput(cardNumberInput)

        // then
        assert(changedValue == "1234")
    }
}
