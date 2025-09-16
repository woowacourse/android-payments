@file:Suppress("ktlint:standard:function-naming")

package woowacourse.payments.ui.cardwallet

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.cardwallet.components.CardWalletContent
import woowacourse.payments.ui.cardwallet.model.CardWalletState
import woowacourse.payments.ui.common.model.CardUiModel

class CardWalletContentTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 카드가_없는_경우_추가_안내_문구를_출력한다() {
        // given
        val cards = emptyList<CardUiModel>()

        // when
        composeRule.setContent {
            CardWalletContent(
                cards = cards,
                cardWalletState = CardWalletState.from(cards.size),
                navigateToNewCard = {},
            )
        }

        // then
        composeRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()
    }

    @Test
    fun 카드가_1장_이하일_때_플러스_버튼을_출력한다() {
        // given
        val cards =
            listOf(
                CardUiModel("1234123412341234", "0511", "공백"),
            )

        // when
        composeRule.setContent {
            CardWalletContent(
                cards = cards,
                cardWalletState = CardWalletState.from(cards.size),
                navigateToNewCard = {},
            )
        }

        // then
        composeRule
            .onNodeWithText("+")
            .assertIsDisplayed()
    }

    @Test
    fun 카드가_1장_초과일_경우_플러스_버튼을_출력하지_않는다() {
        // given
        val cards =
            listOf(
                CardUiModel("1234123412341234", "0511", "공백"),
                CardUiModel("4321432143214321", "0928", "비비"),
            )

        // when
        composeRule.setContent {
            CardWalletContent(
                cards = cards,
                cardWalletState = CardWalletState.from(cards.size),
                navigateToNewCard = {},
            )
        }

        // then
        composeRule
            .onNodeWithText("+")
            .assertDoesNotExist()
    }
}
