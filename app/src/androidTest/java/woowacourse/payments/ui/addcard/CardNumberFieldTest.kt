package woowacourse.payments.ui.addcard

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.CardNumberUiModel

class CardNumberFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var changedValue = ""

    @Before
    fun setup() {
        changedValue = ""
        composeTestRule.setContent {
            CardNumberField(
                cardNumber = CardNumberUiModel("", "", "", ""),
                onValueChange = { changedValue = it },
            )
        }
    }

    @Test
    fun `onValueChange가_올바른_값으로_호출된다`() {
        // given
        val inputField = composeTestRule.onNodeWithText("카드 번호")

        // when
        val cardNumberInput = "1234"
        inputField.performTextInput(cardNumberInput)

        // then
        assert(changedValue == "1234")
    }
}
