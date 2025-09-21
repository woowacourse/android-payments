package woowacourse.payments

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
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

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private lateinit var uiModel: PaymentCardUiModel
    private lateinit var expectedCardNumber: String
    private lateinit var expectedExpiry: String

    @Before
    fun setup() {
        uiModel =
            PaymentCardUiModel(
                cardNumber = "1234567812345678",
                expiry = "0511",
                owner = "minjeong",
                bank = BankType.NOT_SELECTED.toUiModel(),
            )
        val cardSep = context.getString(R.string.card_number_separator)
        val expirySep = context.getString(R.string.expiry_separator)

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
