package woowacourse.payments.ui.screen.cards.component

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.screen.DEFAULT_CARD
import woowacourse.payments.ui.screen.MULTIPLE_CARD
import woowacourse.payments.ui.screen.cards.CardsUiState

class CardsScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `카드가_없을_때_안내_텍스트와_추가_버튼이_나타난다`() {
        // given
        composeRule.setContent {
            CardsScreen()
        }

        // then
        composeRule
            .onNodeWithContentDescription("카드 추가 안내")
            .assert(hasText("새로운 카드를 등록해주세요"))
            .assertIsDisplayed()

        composeRule
            .onNodeWithContentDescription("카드 추가")
            .assert(hasClickAction())
            .assertIsDisplayed()
    }

    @Test
    fun `카드가_1장만_있을_때_카드와_추가_버튼이_나타난다`() {
        // given
        composeRule.setContent {
            CardsScreen(initialState = CardsUiState.SingleCard(DEFAULT_CARD))
        }

        // then
        composeRule
            .onNodeWithContentDescription("카드 번호")
            .assert(hasText(DEFAULT_CARD.formatCardNumber()))
            .assertIsDisplayed()

        composeRule
            .onNodeWithContentDescription("카드 추가")
            .assert(hasClickAction())
            .assertIsDisplayed()
    }

    @Test
    fun `카드가_여러_장_있을_때_카드와_상단에_추가_버튼이_나타난다`() {
        // given
        composeRule.setContent {
            CardsScreen(initialState = CardsUiState.MultipleCards(MULTIPLE_CARD))
        }

        // then
        composeRule
            .onNodeWithContentDescription("카드 추가")
            .assert(hasClickAction())
            .assertIsDisplayed()

        composeRule
            .onAllNodesWithContentDescription("카드 번호")
            .assertCountEquals(MULTIPLE_CARD.size)
    }
}
