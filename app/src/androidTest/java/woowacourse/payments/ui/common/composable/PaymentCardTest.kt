package woowacourse.payments.ui.common.composable

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.CARD_FIXTURE_0
import woowacourse.payments.ui.model.CardUiModel

@Suppress("ktlint:standard:function-naming")
class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드_정보가_없으면_빈_카드가_표시된다() {
        // given
        val card = CardUiModel()

        // when
        composeTestRule.setContent { PaymentCard(card = card) }

        // then
        composeTestRule.onNodeWithTag("payment card card company name").assertTextEquals("")
        composeTestRule.onNodeWithTag("payment card card number").assertTextEquals("")
        composeTestRule.onNodeWithTag("payment card cardholder name").assertTextEquals("")
        composeTestRule.onNodeWithTag("payment card expiration date").assertTextEquals("")
    }

    @Test
    fun 카드_정보가_있으면_카드_정보를_포함한_카드가_표시된다() {
        // given
        val card: CardUiModel = CARD_FIXTURE_0

        // when
        composeTestRule.setContent { PaymentCard(card = card) }

        // then
        composeTestRule.onNodeWithTag("payment card card company name").assertTextEquals("국민카드")
        composeTestRule
            .onNodeWithTag("payment card card number")
            .assertTextEquals("1234 - 1234 - **** - ****")
        composeTestRule.onNodeWithTag("payment card cardholder name").assertTextEquals("디랙")
        composeTestRule.onNodeWithTag("payment card expiration date").assertTextEquals("12 / 99")
    }
}
