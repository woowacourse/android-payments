package woowacourse.payments.ui.cardlist.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R

class CardListTopBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupCardListTopBar(
        showAddCardBtn: Boolean = false,
        onAddCardClick: () -> Unit = {},
    ) {
        composeTestRule.setContent {
            CardListTopBar(
                showAddCardBtn = showAddCardBtn,
                onAddCardClick = onAddCardClick,
            )
        }
    }

    @Test
    fun `CardListTopBar_에_올바른_제목이_표시된다`() {
        // given + when
        setupCardListTopBar()

        // then
        composeTestRule.onNodeWithText("Payments").assertIsDisplayed()
    }

    @Test
    fun `showAddCardBtn이_true이면_카드_추가_버튼이_표시된다`() {
        // given + when
        setupCardListTopBar(showAddCardBtn = true)

        // then
        composeTestRule.onNodeWithText("추가").assertIsDisplayed()
    }

    @Test
    fun `showAddCardBtn이_false이면_카드_추가_버튼이_표시되지_않는다`() {
        // given + when
        setupCardListTopBar(showAddCardBtn = false)

        // then
        composeTestRule.onNodeWithText("추가").assertIsNotDisplayed()
    }

    @Test
    fun `카드_추가_버튼_클릭_시_onAddCardClick이_호출된다`() {
        // given
        var addCardClicked = false
        setupCardListTopBar(showAddCardBtn = true, onAddCardClick = { addCardClicked = true })

        // when
        composeTestRule.onNodeWithText("추가").performClick()

        // then
        assert(addCardClicked)
    }
}
