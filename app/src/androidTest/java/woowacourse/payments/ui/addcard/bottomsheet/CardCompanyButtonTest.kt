package woowacourse.payments.ui.addcard.bottomsheet

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.toUiModel

@Suppress("ktlint:standard:function-naming")
class CardCompanyButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드사에_맞는_카드_정보가_표시된다() {
        // given
        val cardCompany: CardCompanyUiModel = CardCompany.BC_CARD.toUiModel()

        // when
        composeTestRule.setContent { CardCompanyButton(cardCompany, {}) }

        // then
        composeTestRule.onNodeWithText("BC카드").assertIsDisplayed()
    }
}
