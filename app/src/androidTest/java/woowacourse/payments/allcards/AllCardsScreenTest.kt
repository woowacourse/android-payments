package woowacourse.payments.allcards

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.cards
import woowacourse.payments.multipleAllCardUiState
import woowacourse.payments.singleAllCardUiState
import woowacourse.payments.ui.allcards.AllCardsScreen
import woowacourse.payments.ui.allcards.model.AllCardsUiState

@OptIn(ExperimentalTestApi::class)
class AllCardsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 현재_카드_개수만큼_카드를_출력한다() {
        // given
        composeTestRule.setContent {
            AllCardsScreen(
                AllCardsUiState(
                    cards,
                ),
            )
        }

        // when - then
        composeTestRule
            .onAllNodesWithText("홍길동", substring = true)
            .assertCountEquals(3)
    }

    @Test
    fun Single뷰_타입일_떄_등록된_카드와_카드_추가_버튼을_출력한다() {
        // given
        composeTestRule.setContent {
            AllCardsScreen(
                singleAllCardUiState,
            )
        }

        // when - then
        composeTestRule
            .onNodeWithText("홍길동", substring = true)
            .assertExists()

        composeTestRule
            .onNodeWithContentDescription("추가")
            .assertExists()
    }

    @Test
    fun Multiple_뷰_타입일때_가_두개_이상일_때_등록된_카드만_출력한다() {
        // given
        composeTestRule.setContent {
            AllCardsScreen(
                multipleAllCardUiState,
            )
        }

        // when - then
        composeTestRule
            .onNodeWithContentDescription("추가")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText("추가")
            .assertDoesNotExist()
    }

    @Test
    fun 카드_클릭_시_올바른_데이터와_함께_지정된_동작을_수행한다() {
        // given
        var isClicked = false
        val expectedCardInfo = cards.first()
        val expectedCardIndex = 0

        composeTestRule.setContent {
            AllCardsScreen(
                singleAllCardUiState,
                onCardClick = { cardInfo ->
                    isClicked = true
                    assert(expectedCardInfo == cardInfo)
                },
            )
        }

        // when
        composeTestRule
            .onNodeWithText("홍길동", substring = true)
            .performClick()

        // then
        assert(isClicked == true)
    }
}
