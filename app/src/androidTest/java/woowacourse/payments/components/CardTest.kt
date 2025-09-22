package woowacourse.payments.components

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.cards
import woowacourse.payments.ui.component.Card
import woowacourse.payments.ui.uimodel.CardInfoUiState

@OptIn(ExperimentalTestApi::class)
class CardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드번호는_하이픈으로_구분뒤고_뒤_8자리는_마스킹_처리된다() {
        // given
        composeTestRule.setContent {
            Card(
                cardInfoUiState = cards.first(),
                showCardInfo = true,
            )
        }

        // when - then
        composeTestRule
            .onNodeWithText("1234 - 1234 - **** - ****")
            .assertExists()
    }

    @Test
    fun 카드의_만료일은_슬래시로_구분되어_출력한다() {
        // given
        composeTestRule.setContent {
            Card(
                cardInfoUiState = cards.first(),
                showCardInfo = true,
            )
        }

        // when - then
        composeTestRule
            .onNodeWithText("12 / 25")
            .assertExists()
    }

    @Test
    fun 불완전한_카드_정보는_출력하지_않는다() {
        // given
        composeTestRule.setContent {
            Card(
                cardInfoUiState = CardInfoUiState(),
                showCardInfo = true,
            )
        }

        // when - then
        composeTestRule
            .onNodeWithText("홍길동")
            .assertDoesNotExist()
    }

    @Test
    fun 카드를_클릭하면_지정된_동작을_수행한다() {
        // given
        var isClicked = false
        composeTestRule.setContent {
            Card(
                cardInfoUiState = cards.first(),
                showCardInfo = true,
                onClick = { isClicked = true },
            )
        }

        // when
        composeTestRule
            .onNodeWithText("홍길동", substring = true)
            .performClick()

        // then
        assert(isClicked == true)
    }

    @Test
    fun 카드사가_있으면_카드사_이름을_출력한다() {
        // given
        val card = cards.first()
        if (card.vendor == null) throw AssertionError("vendor is null")
        lateinit var context: Context

        composeTestRule.setContent {
            Card(
                cardInfoUiState = card,
                showCardInfo = true,
            )
            context = LocalContext.current
        }

        // when
        val expected = context.getString(card.vendor!!.vendorNameId)

        // then
        composeTestRule
            .onNodeWithText(expected)
            .assertExists()
    }
}
