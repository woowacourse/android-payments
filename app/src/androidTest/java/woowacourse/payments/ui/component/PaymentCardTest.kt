package woowacourse.payments.ui.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel

class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        val paymentCard =
            PaymentCardUiModel(
                number = CardNumberUiModel("1234567812345678"),
                expirationDate = CardExpirationDateUiModel("1224"),
                cardholderName = CardholderNameUiModel("JOHN DOE"),
            )

        composeTestRule.setContent {
            PaymentCard(
                paymentCardUiModel = paymentCard,
                modifier = Modifier.testTag(TEST_TAG),
            )
        }
    }

    @Test
    fun `카드번호는_앞의_8자리만_표시되고_이후는_마스킹된다`() {
        composeTestRule
            .onNodeWithText("1234 - 5678 - **** - ****")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일은_MM_슬래시_YY_형식으로_표시된다`() {
        composeTestRule
            .onNodeWithText("12 / 24")
            .assertIsDisplayed()
    }

    @Test
    fun `카드소유자명이_UI에_그대로_표시된다`() {
        composeTestRule
            .onNodeWithText("JOHN DOE")
            .assertIsDisplayed()
    }

    companion object {
        private const val TEST_TAG = "PaymentCard"
    }
}
