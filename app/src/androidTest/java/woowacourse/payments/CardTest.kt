package woowacourse.payments

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.component.Card
import woowacourse.payments.ui.uimodel.CardInfoUiState

@OptIn(ExperimentalTestApi::class)
class CardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드의_비밀번호는_하이픈으로_구분뒤고_뒤_8자리는_마스킹_처리된다() {
        // given
        composeTestRule.setContent {
            Card(
                cardInfoUiState = cards.first(),
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
            )
        }

        // when - then
        composeTestRule
            .onNodeWithText("홍길동")
            .assertDoesNotExist()
    }
}
