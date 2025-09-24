package woowacourse.payments.ui.cardlist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.newcard.model.CardCompanyUiModel

@Suppress("ktlint:standard:function-naming")
class CardListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드가_없으면_추가_메시지와_카드_추가_버튼이_표시된다() {
        // given
        composeTestRule.setContent {
            CardListScreen(cards = emptyList())
        }

        // when & then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("카드 등록하기")
            .assertIsDisplayed()
    }

    @Test
    fun 카드가_없으면_상단바에_카드_추가_버튼이_표시되지_않는다() {
        // given
        composeTestRule.setContent {
            CardListScreen(cards = emptyList())
        }

        // when & then
        composeTestRule
            .onNodeWithContentDescription("카드 추가하기")
            .assertDoesNotExist()
    }

    @Test
    fun 카드가_1개이면_카드_추가_버튼이_표시된다() {
        // given
        composeTestRule.setContent {
            CardListScreen(cards = listOf(CARD))
        }

        // when & then
        composeTestRule
            .onNodeWithContentDescription("카드 등록하기")
            .assertIsDisplayed()
    }

    @Test
    fun 카드가_1개이면_상단바에_카드_추가_버튼이_표시되지_않는다() {
        // given
        composeTestRule.setContent {
            CardListScreen(cards = listOf(CARD))
        }

        // when & then
        composeTestRule
            .onNodeWithContentDescription("카드 추가하기")
            .assertDoesNotExist()
    }

    @Test
    fun 카드가_2개_이상이면_카드_추가_버튼이_표시되지_않는다() {
        // given
        composeTestRule.setContent {
            CardListScreen(
                cards = List(2) { CARD },
            )
        }

        // then
        composeTestRule
            .onNodeWithContentDescription("카드 등록하기")
            .assertDoesNotExist()
    }

    @Test
    fun 카드가_2개_이상이면_상단바에_카드_추가_버튼이_표시된다() {
        // given
        composeTestRule.setContent {
            CardListScreen(
                cards = List(2) { CARD },
            )
        }

        // when & then
        composeTestRule
            .onNodeWithContentDescription("카드 추가하기")
            .assertIsDisplayed()
    }

    companion object {
        private val COMPANY =
            CardCompanyUiModel(
                name = R.string.bc_card,
                logo = R.drawable.bc,
                color = 0xFFF04651,
            )
        private val CARD =
            CardUiModel(
                cardCompany = COMPANY,
                number = "1111222233334444",
                expirationDate = "0925",
                holderName = "CREW",
            )
    }
}
