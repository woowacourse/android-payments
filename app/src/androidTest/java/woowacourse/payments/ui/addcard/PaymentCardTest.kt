package woowacourse.payments.ui.addcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.BankTypeUiModel

class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupPaymentCard(bank: BankTypeUiModel = BankTypeUiModel.KB) {
        composeTestRule.setContent {
            PaymentCard(bank = bank)
        }
    }

    @Test
    fun `은행_이름이_올바르게_표시된다`() {
        // given + when
        setupPaymentCard(bank = BankTypeUiModel.KB)

        // then
        composeTestRule.onNodeWithText("국민카드").assertIsDisplayed()
    }
}
