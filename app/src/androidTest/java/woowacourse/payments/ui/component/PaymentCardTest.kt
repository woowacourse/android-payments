package woowacourse.payments.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel

class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드_정보가_존재하는_경우_정보를_확인할_수_있다`() {
        // given && when
        val paymentCardInformation =
            CardUiModel(
                cardholderNameUiModel = CardholderNameUiModel("CREW"),
                cardNumberUiModel = CardNumberUiModel("1111222233334444"),
                cardExpirationDateUiModel = CardExpirationDateUiModel("0421"),
            )
        composeTestRule.setContent { PaymentCard(card = paymentCardInformation) }

        // then
        composeTestRule
            .onNodeWithContentDescription("카드 정보")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_정보가_없다면_정보_영역은_보여지지_않는다`() {
        // given && when
        val paymentCardInformation = null
        composeTestRule.setContent { PaymentCard(card = paymentCardInformation) }

        // then
        composeTestRule
            .onNodeWithContentDescription("카드 정보")
            .assertIsNotDisplayed()
    }
}
