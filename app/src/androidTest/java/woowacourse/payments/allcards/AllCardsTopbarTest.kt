package woowacourse.payments.allcards

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.cards
import woowacourse.payments.ui.allcards.component.AllCardsTopbar
import woowacourse.payments.ui.allcards.model.AllCardsUiState
import woowacourse.payments.ui.uimodel.CardInfoUiState

@OptIn(ExperimentalTestApi::class)
class AllCardsTopbarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드_개수가_1개_이하라면_추가_버튼이_표시되지_않는다() {
        // given
        composeTestRule.setContent {
            AllCardsTopbar(
                AllCardsUiState(
                    listOf(),
                ),
            )
        }

        // when - then
        composeTestRule
            .onNodeWithContentDescription("추가")
            .assertDoesNotExist()
    }

    @Test
    fun 카드_개수가_2개_이상이면_추가_버튼이_표시된다() {
        // given
        composeTestRule.setContent {
            AllCardsTopbar(
                AllCardsUiState(
                    listOf(
                        CardInfoUiState(),
                        CardInfoUiState(),
                    ),
                ),
            )
        }

        // when - then
        composeTestRule
            .onNodeWithText("추가")
            .assertExists()
    }

    @Test
    fun 추가_버튼을_누르면_카드_추가_동작이_실행된다() {
        // given
        var isClicked = false
        composeTestRule.setContent {
            AllCardsTopbar(
                AllCardsUiState(
                    cards =
                        listOf(
                            CardInfoUiState(),
                            CardInfoUiState(),
                        ),
                ),
                onPlusCardClick = {
                    isClicked = true
                },
            )
        }

        // when
        composeTestRule
            .onNodeWithText("추가")
            .performClick()

        // then
        assert(isClicked == true)
    }
}
