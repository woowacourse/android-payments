package woowacourse.payments

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.format.formattedExpiry
import woowacourse.payments.ui.format.maskedCardNumber
import woowacourse.payments.ui.model.PaymentCardUiModel

class PaymentCardTest {
    @get:Rule
    val composeRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun 카드_정보가_정상적으로_표시된다() {
        // given
        val uiModel =
            PaymentCardUiModel(
                cardNumber = "1234567812345678",
                expiry = "0511",
                owner = "minjeong",
            )
        val cardSep = context.getString(R.string.card_number_separator)
        val expirySep = context.getString(R.string.expiry_separator)

        val expectedCardNumber = "1234567812341234".maskedCardNumber(cardSep)
        val expectedExpiry = "0511".formattedExpiry(expirySep)

        // when
        composeRule.setContent {
            PaymentCard(
                modifier = Modifier.testTag(Tags.PAYMENT_CARD),
                paymentCard = uiModel,
            )
        }

        // then
        composeRule.onNodeWithTag(Tags.PAYMENT_CARD).assertIsDisplayed()
        composeRule.onNodeWithText(expectedCardNumber).assertIsDisplayed()
        composeRule.onNodeWithText("minjeong").assertIsDisplayed()
        composeRule.onNodeWithText(expectedExpiry).assertIsDisplayed()
    }
}
