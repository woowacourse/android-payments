@file:Suppress("ktlint:standard:function-naming")

package woowacourse.payments.ui.cardwallet.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.cardwallet.components.PaymentCard
import woowacourse.payments.ui.common.model.CardUiModel

class PaymentCardTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 카드_정보를_출력한다() {
        // given
        val card =
            CardUiModel(
                numberDigits = "1234123412341234",
                expiry = "0511",
                holder = "공백",
                bankType = BankType.HYUNDAI,
            )

        // when
        composeRule.setContent {
            PaymentCard(card = card)
        }

        // then
        composeRule
            .onNodeWithText("1234 - 1234 - **** - ****")
            .assertIsDisplayed()
        composeRule
            .onNodeWithText("공백")
            .assertIsDisplayed()
        composeRule
            .onNodeWithText("05 / 11")
            .assertIsDisplayed()
    }

    @Test
    fun 빈_카드는_텍스트를_표시하지_않는다() {
        // given & when
        composeRule.setContent {
            PaymentCard(card = null)
        }

        // then
        composeRule
            .onNodeWithText(" - ")
            .assertDoesNotExist()
    }
}
