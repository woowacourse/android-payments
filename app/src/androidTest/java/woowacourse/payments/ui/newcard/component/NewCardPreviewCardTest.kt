@file:Suppress("ktlint:standard:function-naming")

package woowacourse.payments.ui.newcard.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.newcard.components.NewCardPreviewCard

class NewCardPreviewCardTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 은행이_선택되지_않으면_라벨이_표시되지_않는다() {
        // given & when
        composeRule.setContent {
            NewCardPreviewCard(bankType = BankType.NOT_SELECTED)
        }

        // then
        composeRule.onNodeWithText("신한카드").assertDoesNotExist()
        composeRule.onNodeWithText("국민카드").assertDoesNotExist()
    }

    @Test
    fun 은행이_선택되면_라벨이_표시된다() {
        // given
        val bank = BankType.SHINHAN

        // when
        composeRule.setContent {
            NewCardPreviewCard(bankType = bank)
        }

        // then
        composeRule.onNodeWithText("신한카드").assertIsDisplayed()
    }
}
