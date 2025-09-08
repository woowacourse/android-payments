package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.CardUiModel
import woowacourse.payments.ui.screen.cardList.CardListScreen

class CardListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var card1: CardUiModel
    private lateinit var card2: CardUiModel

    @Before
    fun setUp() {
        card1 = CardUiModel("1234-5678-8765-4321", "1221", "aaaa")
        card2 = CardUiModel("8765-4321-1234-5678", "1122", "bbbb")
    }

    @Test
    fun 카드가_0개일_때_새로운_카드_등록_텍스트와_카드_추가_박스가_표시된다() {
        // given
        val cards = emptyList<CardUiModel>()

        // when
        composeTestRule.setContent {
            CardListScreen(
                cards = cards,
                navigateToAddCard = {},
            )
        }

        // then
        composeTestRule.onNodeWithContentDescription("새로운 카드 등록 텍스트").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("카드 추가").assertIsDisplayed()
    }

    @Test
    fun 카드가_1개일_때_카드_정보와_카드_추가_박스가_표시된다() {
        // given
        val cards = listOf(card1)

        // when
        composeTestRule.setContent {
            CardListScreen(
                cards = cards,
                navigateToAddCard = {},
            )
        }

        // then
        composeTestRule.onNodeWithText(card1.number).assertIsDisplayed()
        composeTestRule.onNodeWithText(card1.expired).assertIsDisplayed()
        composeTestRule.onNodeWithText(card1.owner).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("카드 추가").assertIsDisplayed()
    }

    @Test
    fun 카드가_2개이상일_때_카드_정보와_TopBar에_추가_텍스트가_표시된다() {
        // given
        val cards = listOf(card1, card2)

        // when
        composeTestRule.setContent {
            CardListScreen(
                cards = cards,
                navigateToAddCard = {},
            )
        }

        // then
        cards.forEach { card ->
            composeTestRule.onNodeWithText(card.number).assertIsDisplayed()
            composeTestRule.onNodeWithText(card.expired).assertIsDisplayed()
            composeTestRule.onNodeWithText(card.owner).assertIsDisplayed()
        }
        composeTestRule.onNodeWithContentDescription("카드 추가").assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription("TopBar 카드 추가 버튼").assertIsDisplayed()
    }
}
