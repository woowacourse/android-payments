package woowacourse.payments.ui.addcard

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.ui.model.OwnerNameUiModel

class CardOwnerFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupCardOwnerField(
        cardOwner: OwnerNameUiModel = OwnerName.fromRawInput("").toUiModel(),
        onValueChange: (String) -> Unit = {},
    ) {
        composeTestRule.setContent {
            CardOwnerField(
                cardOwner = cardOwner,
                onValueChange = onValueChange,
            )
        }
    }

    @Test
    fun `onValueChange가_올바른_값으로_호출된다`() {
        // given
        var changedValue = ""
        setupCardOwnerField(onValueChange = { changedValue = it })
        val inputField = composeTestRule.onNodeWithText("카드 소유자 이름(선택)")

        // when
        val ownerNameInput = "TEST"
        inputField.performTextInput(ownerNameInput)

        // then
        assert(changedValue == "TEST")
    }

    @Test
    fun `글자_수가_올바르게_표시된다`() {
        // given + when
        setupCardOwnerField(cardOwner = OwnerName.fromRawInput("TEST").toUiModel())

        // then
        composeTestRule.onNodeWithText("4/30").assertExists()
    }
}
