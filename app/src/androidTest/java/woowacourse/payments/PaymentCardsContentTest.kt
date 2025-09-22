package woowacourse.payments

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.component.PaymentCardsContent
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.mapper.toUiModel

class PaymentCardsContentTest {
    @get:Rule
    val composeRule = createComposeRule()

    private fun setContentWithTag(
        paymentCards: List<PaymentCardUiModel>,
        tag: String,
    ) {
        composeRule.setContent {
            Box(Modifier.testTag(tag)) {
                PaymentCardsContent(
                    modifier = Modifier,
                    paymentCards = paymentCards,
                    onAddCard = {},
                )
            }
        }
    }

    @Test
    fun 빈_리스트면_EmptyCard_표시() {
        setContentWithTag(emptyList(), Tags.EMPTY_CARD)
        composeRule.onAllNodesWithTag(Tags.EMPTY_CARD).assertCountEquals(1)
        composeRule.onNodeWithTag(Tags.EMPTY_CARD).assertIsDisplayed()
    }

    @Test
    fun 한개면_SingleCard_표시() {
        val one = listOf(PaymentCardUiModel("1234567812345678", "0527", "Alice", BankType.NOT_SELECTED.toUiModel()))
        setContentWithTag(one, Tags.SINGLE_CARD)
        composeRule.onNodeWithTag(Tags.SINGLE_CARD).assertIsDisplayed()
    }

    @Test
    fun 두개_이상이면_MultiCards_표시() {
        val many =
            listOf(
                PaymentCardUiModel("1234567812345678", "0527", "Alice", BankType.NOT_SELECTED.toUiModel()),
                PaymentCardUiModel("8765432187654321", "1128", "Bob", BankType.NOT_SELECTED.toUiModel()),
            )
        setContentWithTag(many, Tags.MULTI_CARDS)
        composeRule.onNodeWithTag(Tags.MULTI_CARDS).assertIsDisplayed()
    }
}
