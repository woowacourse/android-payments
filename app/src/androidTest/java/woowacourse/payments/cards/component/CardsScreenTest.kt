package woowacourse.payments.cards.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.NO_CARD
import woowacourse.payments.ONE_CARD
import woowacourse.payments.THREE_CARD

class CardsScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `카드_목록이_비어있을_때에는_새_카드를_등록하라는_안내가_노출된다`() {
        // given

        composeRule.setContent {
            CardsScreen(
                cards = NO_CARD,
                addCard = {},
            )
        }

        // then
        composeRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_목록이_비어있을_때_카드_추가_화면으로_이동할_수_있다`() {
        // given
        composeRule.setContent {
            CardsScreen(
                cards = NO_CARD,
                addCard = {},
            )
        }

        // when
        composeRule
            .onNodeWithContentDescription("새 카드 등록 버튼")
            .performClick()

        // then
        composeRule
            .onNodeWithTag("CardAdditionScreen")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_목록에_카드가_한_개_있을_때_카드_추가_UI가_노출된다`() {
        // given
        composeRule.setContent {
            CardsScreen(
                cards = ONE_CARD,
                addCard = {},
            )
        }

        // then
        composeRule
            .onNodeWithContentDescription("새 카드 등록 버튼")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_목록에_카드가_한_개_있을_때_카드_추가_화면으로_이동할_수_있다`() {
        // given
        composeRule.setContent {
            CardsScreen(
                cards = ONE_CARD,
                addCard = {},
            )
        }

        // when
        composeRule
            .onNodeWithContentDescription("새 카드 등록 버튼")
            .performClick()

        // then
        composeRule
            .onNodeWithTag("CardAdditionScreen")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_목록에_카드가_여러_개_있을_때_카드_추가_UI가_노출된다`() {
        // given
        composeRule.setContent {
            CardsScreen(
                cards = THREE_CARD,
                addCard = {},
            )
        }

        // then
        composeRule.onNodeWithContentDescription("새 카드 등록 버튼")
    }

    @Test
    fun `카드_목록에_카드가_여러_개_있을_때_카드_추가_화면으로_이동할_수_있다`() {
        // given
        composeRule.setContent {
            CardsScreen(
                cards = THREE_CARD,
                addCard = {},
            )
        }

        // when
        composeRule
            .onNodeWithContentDescription("새 카드 등록 버튼")
            .performClick()

        // then
        composeRule
            .onNodeWithTag("CardAdditionScreen")
            .assertIsDisplayed()
    }
}
