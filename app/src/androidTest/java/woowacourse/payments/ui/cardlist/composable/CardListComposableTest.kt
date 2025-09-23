package woowacourse.payments.ui.cardlist.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import kotlinx.collections.immutable.toImmutableList
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.Card

class CardListComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupCardListComposable() {
        composeTestRule.setContent {
            CardListScreen(
                emptyList<Card>().toImmutableList(),
                onEditCard = {},
                onAddCard = {},
                onChangeIndex = {},
            )
        }
    }

    @Test
    fun `카드_목록이_비어있을_때_메시지가_표시된다`() {
        // given + when
        setupCardListComposable()

        // then
        composeTestRule.onNodeWithText("새로운 카드를 등록해주세요").assertIsDisplayed()
    }
}
