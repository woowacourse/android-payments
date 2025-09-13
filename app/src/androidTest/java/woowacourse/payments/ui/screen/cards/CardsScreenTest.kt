package woowacourse.payments.ui.screen.cards

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel

class CardsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `존재하는_카드가_없다면_등록_권유_문구와_카드_추가_박스가_보여진다`() {
        // given && when
        composeTestRule.setContent {
            CardsScreen(
                onRegistrationClick = {},
                uiState = CardsScreenUiState(emptyList()),
            )
        }

        // then
        composeTestRule.run {
            onNodeWithText("새로운 카드를 등록해주세요").assertIsDisplayed()
            onNodeWithContentDescription("추가 박스 영역").assertIsDisplayed()
        }
    }

    @Test
    fun `존재하는_카드가_1개라면_카드화면과_카드_추가_박스가_보여진다`() {
        // given && when
        composeTestRule.setContent {
            CardsScreen(
                onRegistrationClick = {},
                uiState =
                    CardsScreenUiState(
                        listOf(
                            CardUiModel(
                                cardholderNameUiModel = CardholderNameUiModel("ABC"),
                                cardNumberUiModel = CardNumberUiModel("1111222233334444"),
                                cardExpirationDateUiModel = CardExpirationDateUiModel("0999"),
                            ),
                        ),
                    ),
            )
        }

        // then
        composeTestRule.run {
            onNodeWithContentDescription("카드 정보").assertIsDisplayed()
            onNodeWithContentDescription("추가 박스 영역").assertIsDisplayed()
        }
    }

    @Test
    fun `존재하는_카드가_2개_이상이라면_카드_추가_박스는_안보이고_카드화면과_앱바_추가_버튼이_보여진다`() {
        // given && when
        composeTestRule.setContent {
            CardsScreen(
                onRegistrationClick = {},
                uiState =
                    CardsScreenUiState(
                        listOf(
                            CardUiModel(
                                cardholderNameUiModel = CardholderNameUiModel("ABC"),
                                cardNumberUiModel = CardNumberUiModel("1111222233334444"),
                                cardExpirationDateUiModel = CardExpirationDateUiModel("0999"),
                            ),
                            CardUiModel(
                                cardholderNameUiModel = CardholderNameUiModel("ABC2"),
                                cardNumberUiModel = CardNumberUiModel("1111222233335555"),
                                cardExpirationDateUiModel = CardExpirationDateUiModel("0888"),
                            ),
                        ),
                    ),
            )
        }

        // then
        composeTestRule.run {
            onNodeWithText("1111222233334444").isDisplayed()
            onNodeWithText("1111222233335555").isDisplayed()
            onNodeWithContentDescription("추가 박스 영역").assertIsNotDisplayed()
            onNodeWithContentDescription("카드 목록 앱 바 추가").assertIsDisplayed()
        }
    }
}
