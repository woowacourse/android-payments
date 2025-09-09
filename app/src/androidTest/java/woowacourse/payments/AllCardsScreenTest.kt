package woowacourse.payments

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.allcards.AllCardsScreen
import woowacourse.payments.ui.allcards.component.PlusCard

@OptIn(ExperimentalTestApi::class)
class AllCardsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 현재_카드_개수만큼_카드를_출력한다() {
        //given
        composeTestRule.setContent {
            AllCardsScreen(
                cards = cards
            )
        }

        //when - then
        composeTestRule
            .onAllNodesWithText("홍길동", substring = true)
            .assertCountEquals(3)
    }

    @Test
    fun 카드가_없을_때_등록_안내_문구와_카드_추가_버튼을_출력한다() {
        //given
        composeTestRule.setContent {
            AllCardsScreen(
                cards = mutableStateListOf()
            )
        }

        //when - then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertExists()

        composeTestRule
            .onNodeWithContentDescription("추가")
            .assertExists()
    }

    @Test
    fun 카드가_하나일_떄_등록된_카드와_카드_추가_버튼을_출력한다() {
        //given
        composeTestRule.setContent {
            AllCardsScreen(
                cards = mutableStateListOf(
                    cards[1]
                )
            )
        }

        //when - then
        composeTestRule
            .onNodeWithText("홍길동")
            .assertExists()

        composeTestRule
            .onNodeWithContentDescription("추가")
            .assertExists()
    }

    @Test
    fun 카드가_두개_이상일_때_등록된_카드만_출력한다() {
        //given
        composeTestRule.setContent {
            AllCardsScreen(
                cards = cards
            )
        }

        //when - then
        composeTestRule
            .onAllNodesWithText("홍길동")

        composeTestRule
            .onNodeWithContentDescription("추가")
            .assertDoesNotExist()
    }

    @Test
    fun 카드_추가_버튼을_클릭하면_카드_추가_동작을_수행한다() {
        //given
        var isClicked = false
        composeTestRule.setContent {
            PlusCard(
                onClick = {
                    isClicked = true
                }
            )
        }

        //when
        composeTestRule
            .onNodeWithContentDescription("추가")
            .performClick()

        //then
        assert(isClicked == true)
    }
}