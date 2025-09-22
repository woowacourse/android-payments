package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.list.CardListScreen
import woowacourse.payments.newCard.CardScreenUiState

@Suppress("ktlint:standard:function-naming")
class EmptyCardListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CardListScreen(uiState = CardScreenUiState.from(emptyList()), onAddClick = {})
        }
    }

    @Test
    fun 추가된_카드가_없으면_카드_추가_안내가_보인다() {
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()
    }

    @Test
    fun 추가된_카드가_없으면_탑바에_추가_버튼이_없다() {
        composeTestRule
            .onNodeWithText("추가")
            .assertDoesNotExist()
    }

    @Test
    fun 추가된_카드가_없으면_카드_추가_뷰가_보인다() {
        composeTestRule
            .onNode(hasContentDescription("새로운 카드 추가"))
            .assertIsDisplayed()
    }
}
