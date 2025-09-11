package woowacourse.payments.cards

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.Card
import woowacourse.payments.fixture.CARD_CREAM
import woowacourse.payments.fixture.CARD_UNNAMED
import woowacourse.payments.fixture.CARD_YAGUBOGU

class CardsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드_목록이_없으면_카드_등록_유도_문구가_표시된다`() {
        // given
        val cards = emptyList<Card>()

        // when
        composeTestRule.setContent {
            CardsScreen()
        }

        // then
        composeTestRule.onNodeWithText("새로운 카드를 등록해주세요.").assertIsDisplayed()
    }

    @Test
    fun `카드_목록에_1개_이하의_카드가_있으면_목록_하단에_카드_추가_UI가_표시된다`() {
        // given
        val cards: List<Card> = listOf(CARD_CREAM)

        // when
        composeTestRule.setContent {
            CardsScreen()
        }

        // then
        composeTestRule.onNodeWithTag("카드 추가", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun `카드_목록에_여러_카드가_있으면_상단바에_카드_추가_UI가_표시된다`() {
        // given
        val cards: List<Card> = listOf(CARD_CREAM, CARD_UNNAMED, CARD_YAGUBOGU)

        // given & when
        composeTestRule.setContent {
            CardsScreen(
                cardsStateHolder =
                    CardsStateHolder().apply {
                        cards.forEach { card -> add(card) }
                    },
            )
        }

        // then
        composeTestRule.onNodeWithTag("카드 추가").assertIsNotDisplayed()
        composeTestRule.onNode(hasClickAction() and hasText("추가")).assertIsDisplayed()
    }
}
