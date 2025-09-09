package woowacourse.payments.ui.screen.cards.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.screen.MULTIPLE_CARD
import woowacourse.payments.ui.screen.SINGLE_CARD

class CardsScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 카드가_없을_때_안내_텍스트와_추가_버튼이_나타난다() {
        // given
        composeRule.setContent {
            CardsScreen()
        }

        // then
        composeRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()

        composeRule
            .onNodeWithContentDescription("카드 추가 버튼")
            .assertIsDisplayed()
    }

    @Test
    fun 카드가_1장만_있을_때_카드와_추가_버튼이_나타난다() {
        // given
        composeRule.setContent {
            CardsScreen(cards = SINGLE_CARD)
        }

        // then
        composeRule
            .onNodeWithText("1234 - 5678 - **** - ****")
            .assertIsDisplayed()

        composeRule
            .onNodeWithContentDescription("카드 추가 버튼")
            .assertIsDisplayed()
    }

    @Test
    fun 카드가_여러_장_있을_때_카드와_상단에_추가_버튼이_나타난다() {
        // given
        composeRule.setContent {
            CardsScreen(cards = MULTIPLE_CARD)
        }

        // then
        composeRule
            .onNodeWithText("추가")
            .assertIsDisplayed()

        MULTIPLE_CARD.forEach { card ->
            composeRule
                .onNodeWithText(card.formatCardNumber())
                .assertIsDisplayed()
        }
    }
}