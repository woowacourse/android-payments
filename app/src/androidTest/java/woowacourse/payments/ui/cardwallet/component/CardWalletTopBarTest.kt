@file:Suppress("ktlint:standard:function-naming")

package woowacourse.payments.ui.cardwallet.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.cardwallet.components.CardWalletTopBar

class CardWalletTopBarTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 카드가_1장_초과일_때_추가_버튼을_출력한다() {
        // given
        val cardCount = 2

        // when
        composeRule.setContent {
            CardWalletTopBar(
                cardCount = cardCount,
                onAddClick = {},
            )
        }

        // then
        composeRule
            .onNodeWithText("추가")
            .assertIsDisplayed()
    }

    @Test
    fun 카드가_1장_이하일_때는_추가_버튼을_출력하지_않는다() {
        // given
        val cardCount = 1

        // when
        composeRule.setContent {
            CardWalletTopBar(
                cardCount = cardCount,
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
