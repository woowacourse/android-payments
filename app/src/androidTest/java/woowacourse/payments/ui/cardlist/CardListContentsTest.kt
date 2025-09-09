package woowacourse.payments.ui.cardlist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardholderName
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.domain.Passcode
import java.time.YearMonth

@Suppress("ktlint:standard:function-naming")
class CardListContentsTest {
    val fixture: Card =
        Card(
            CardNumber("1234123412341234"),
            ExpirationDate(YearMonth.of(2034, 12)),
            CardholderName("디랙"),
            Passcode("1234"),
        )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드가_하나도_없을_때는_카드_추가_안내_문구가_표시된다() {
        // given
        val cards = mutableStateListOf<Card>()

        // when
        composeTestRule.setContent { CardListContents(cards) {} }

        // then
        composeTestRule.onNodeWithText("새로운 카드를 등록해주세요").assertIsDisplayed()
    }

    @Test
    fun 카드가_한_개_이상일_때는_카드_추가_안내_문구가_표시되지_않는다() {
        // given
        val cards = mutableStateListOf(fixture)

        // when
        composeTestRule.setContent { CardListContents(cards) {} }

        // then
        composeTestRule.onNodeWithText("새로운 카드를 등록해주세요").assertIsNotDisplayed()
    }

    @Test
    fun 카드가_한_개_이하일_때는_카드_추가_버튼이_기호로_표시된다() {
        // given
        val cards = mutableStateListOf(fixture)

        // when
        composeTestRule.setContent { CardListContents(cards) {} }

        // then
        composeTestRule.onNodeWithText("+").assertIsDisplayed()
        composeTestRule.onNodeWithText("추가").assertIsNotDisplayed()
    }

    @Test
    fun 카드가_두_개_이상일_때는_카드_추가_버튼이_문구로_표시된다() {
        // given
        val cards = mutableStateListOf(fixture, fixture)

        // when
        composeTestRule.setContent { CardListContents(cards) {} }

        // then
        composeTestRule.onNodeWithText("추가").assertIsDisplayed()
        composeTestRule.onNodeWithText("+").assertIsNotDisplayed()
    }
}
