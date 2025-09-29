package woowacourse.payments.ui.common.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R
import woowacourse.payments.ui.cardupdate.model.CardCompanyUiModel
import woowacourse.payments.ui.common.model.CardUiModel

@Suppress("ktlint:standard:function-naming")
class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 모든_카드_정보가_올바르게_표시된다() {
        // given
        composeTestRule.setContent {
            PaymentCard(card = CARD)
        }

        // then
        composeTestRule
            .onNodeWithText("BC카드")
            .assertExists()

        composeTestRule
            .onNodeWithText("1111 - 2222 - **** - ****")
            .assertExists()

        composeTestRule
            .onNodeWithText("CREW")
            .assertExists()

        composeTestRule
            .onNodeWithText("09 / 25")
            .assertExists()
    }

    companion object {
        private val COMPANY =
            CardCompanyUiModel(
                name = R.string.bc_card,
                logo = R.drawable.bc,
                color = 0xFFF04651,
            )
        private val CARD =
            CardUiModel(
                cardCompany = COMPANY,
                number = "1111222233334444",
                expirationDate = "0925",
                holderName = "CREW",
                password = "1234",
            )
    }
}
