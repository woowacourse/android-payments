@file:Suppress("ktlint:standard:function-naming")

package woowacourse.payments.ui.newcard.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.newcard.components.BankSelector

class BankSelectorTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 바텀시트가_열리면_은행_리스트를_표시한다() {
        // given & when
        composeRule.setContent {
            BankSelector(
                isOpen = true,
                selected = BankType.NOT_SELECTED,
                onDismiss = {},
                onSelected = {},
            )
        }

        // then
        composeRule.onNodeWithText("BC카드").assertIsDisplayed()
        composeRule.onNodeWithText("신한카드").assertIsDisplayed()
    }

    @Test
    fun 은행을_선택하면_onSelected가_호출된다() {
        // given
        var selected: BankType = BankType.NOT_SELECTED

        composeRule.setContent {
            BankSelector(
                isOpen = true,
                selected = BankType.NOT_SELECTED,
                onDismiss = {},
                onSelected = { selected = it },
            )
        }

        // when
        composeRule.onNodeWithText("카카오뱅크").performClick()

        // then
        assert(selected == BankType.KAKAO_BANK)
    }
}
