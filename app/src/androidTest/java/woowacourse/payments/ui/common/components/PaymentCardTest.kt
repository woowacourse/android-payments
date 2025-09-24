package woowacourse.payments.ui.common.components

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.newcard.model.CardCompanyUiModel

@Suppress("ktlint:standard:function-naming")
class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드_정보가_없으면_빈필드가_표시된다() {
        // when
        composeTestRule.setContent {
            PaymentCard(card = null)
        }

        // then
        composeTestRule
            .onNodeWithContentDescription("카드사")
            .assertTextEquals("")

        composeTestRule
            .onNodeWithContentDescription("카드 번호")
            .assertTextEquals("")

        composeTestRule
            .onNodeWithContentDescription("카드 소유자")
            .assertTextEquals("")

        composeTestRule
            .onNodeWithContentDescription("만료일")
            .assertTextEquals("")
    }

    @Test
    fun 모든_카드_정보가_올바르게_표시된다() {
        // given
        composeTestRule.setContent {
            PaymentCard(card = CARD)
        }

        // then
        composeTestRule
            .onNodeWithContentDescription("카드사")
            .assertExists("BC카드")

        composeTestRule
            .onNodeWithContentDescription("카드 번호")
            .assertExists("1111 - 2222 - **** - ****")

        composeTestRule
            .onNodeWithContentDescription("카드 소유자")
            .assertExists("CREW")

        composeTestRule
            .onNodeWithContentDescription("만료일")
            .assertExists("09 / 25")
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
