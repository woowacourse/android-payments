package woowacourse.payments.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertAll
import woowacourse.payments.CardUiModelFixture
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.screen.cardList.CardListScreen
import woowacourse.payments.ui.screen.cardList.CardListStateHolder
import woowacourse.payments.ui.screen.cardList.CardListUiState

class CardListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var card1: CardUiModel
    private lateinit var card2: CardUiModel

    @Before
    fun setUp() {
        card1 = CardUiModelFixture.card1
        card2 = CardUiModelFixture.card2
    }

    @Test
    fun 카드가_0개일_때_새로운_카드_등록_텍스트와_카드_추가_박스가_표시된다() {
        // given
        val testStateHolder =
            CardListStateHolder(
                CardListUiState(
                    cards = emptyList(),
                ),
            )

        // when
        composeTestRule.setContent {
            CardListScreen(
                stateHolder = testStateHolder,
                navigateToAddCard = {},
            )
        }

        // then
        assertAll(
            { composeTestRule.onNodeWithText("새로운 카드를 등록해주세요").assertIsDisplayed() },
            { composeTestRule.onNodeWithContentDescription("카드 추가").assertIsDisplayed() },
        )
    }

    @Test
    fun 카드가_1개일_때_카드_정보와_카드_추가_박스가_표시된다() {
        // given
        val testStateHolder =
            CardListStateHolder(
                CardListUiState(
                    cards = listOf(card1),
                ),
            )

        // when
        composeTestRule.setContent {
            CardListScreen(
                stateHolder = testStateHolder,
                navigateToAddCard = {},
            )
        }

        // then
        assertAll(
            { composeTestRule.onNodeWithText(card1.formattedNumber).assertIsDisplayed() },
            { composeTestRule.onNodeWithText(card1.formattedExpired).assertIsDisplayed() },
            { composeTestRule.onNodeWithText(card1.owner).assertIsDisplayed() },
            { composeTestRule.onNodeWithContentDescription("카드 추가").assertIsDisplayed() },
        )
    }

    @Test
    fun 카드가_2개이상일_때_카드_정보와_TopBar에_추가_텍스트가_표시된다() {
        // given
        val cards = listOf(card1, card2)
        val testStateHolder =
            CardListStateHolder(
                CardListUiState(
                    cards = cards,
                ),
            )

        // when
        composeTestRule.setContent {
            CardListScreen(
                stateHolder = testStateHolder,
                navigateToAddCard = {},
            )
        }

        // then
        cards.forEach { card ->
            assertAll(
                { composeTestRule.onNodeWithText(card.formattedNumber).assertIsDisplayed() },
                { composeTestRule.onNodeWithText(card.formattedExpired).assertIsDisplayed() },
                { composeTestRule.onNodeWithText(card.owner).assertIsDisplayed() },
            )
        }
        composeTestRule.onNodeWithContentDescription("카드 추가").assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription("카드 목록 상단 추가 텍스트").assertIsDisplayed()
    }
}
