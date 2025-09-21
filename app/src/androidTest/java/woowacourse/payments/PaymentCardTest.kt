package woowacourse.payments

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.mapper.toUiModel

class PaymentCardTest {
    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var expectedCardNumber: String
    private lateinit var expectedExpiry: String

    @Before
    fun setup() {
        val uiModel =
            PaymentCardUiModel(
                cardNumber = "1234567812345678",
                expiry = "0511",
                owner = "minjeong",
                bank = BankType.NOT_SELECTED.toUiModel(),
            )
        val cardSep = "\u00A0-\u00A0"
        val expirySep = "\u00A0/\u00A0"

        expectedCardNumber = uiModel.maskedCardNumber(cardSep)
        expectedExpiry = uiModel.formattedExpiry(expirySep)

        composeRule.setContent {
            PaymentCard(
                paymentCard = uiModel,
                modifier = Modifier.testTag(Tags.PAYMENT_CARD),
                onSelectBank = {},
            )
        }
    }

    @Test
    fun 카드가_표시된다() {
        composeRule.onNodeWithTag(Tags.PAYMENT_CARD).assertIsDisplayed()
    }

    @Test
    fun 카드번호가_마스킹되어_표시된다() {
        composeRule.onNodeWithText(expectedCardNumber).assertIsDisplayed()
    }

    @Test
    fun 소유자_이름이_표시된다() {
        composeRule.onNodeWithText("minjeong").assertIsDisplayed()
    }

    @Test
    fun 만료일이_형식에_맞게_표시된다() {
        composeRule.onNodeWithText(expectedExpiry).assertIsDisplayed()
    }
}
