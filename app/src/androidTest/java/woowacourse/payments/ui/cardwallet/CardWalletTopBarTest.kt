@file:Suppress("ktlint:standard:function-naming")

package woowacourse.payments.ui.cardwallet

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.cardwallet.components.CardWalletTopBar
import woowacourse.payments.ui.cardwallet.model.CardWalletState

class CardWalletTopBarTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 카드가_3장_이상일_때_추가_버튼을_출력한다() {
        // given
        val state = CardWalletState.MULTIPLE

        // when
        composeRule.setContent {
            CardWalletTopBar(
                state = state,
                onAddClick = {},
            )
        }

        // then
        composeRule
            .onNodeWithText("추가")
            .assertIsDisplayed()
    }

    @Test
    fun 카드가_3장_미만일_때는_추가_버튼을_출력하지_않는다() {
        // given
        val state = CardWalletState.SINGLE

        // when
        composeRule.setContent {
            CardWalletTopBar(
                state = state,
                onAddClick = {},
            )
        }

        // then
        composeRule
            .onNodeWithText("Payments")
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("추가")
            .assertDoesNotExist()
    }
}
