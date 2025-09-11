package woowacourse.payments.ui.cardlist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardDigit
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardHolderName
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.ui.common.model.CardUiModel
import java.time.YearMonth

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
            .onNode(hasContentDescription("카드 등록하기"))
            .assertIsDisplayed()
    }

    @Test
    fun 카드가_1개일_때_카드_추가_버튼이_표시된다() {
        // given
        composeTestRule.setContent {
            CardListScreen(cards = listOf(CARD))
        }

        // when & then
        composeTestRule
            .onNode(hasContentDescription("카드 등록하기"))
            .assertIsDisplayed()
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
            .onNode(hasContentDescription("카드 등록하기"))
            .assertIsNotDisplayed()
    }

    companion object {
        private val CARD =
            CardUiModel(
                number = "1111 - 2222 - 3333 - 4444",
                expirationDate = "09 / 25",
                holderName = "CREW",
            )
    }
}
