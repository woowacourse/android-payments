package woowacourse.payments.ui.cards

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.cards.fixture.CARD_ONE
import woowacourse.payments.ui.cards.fixture.CARD_TWO
import woowacourse.payments.ui.cards.fixture.FakeCardsRepositoryFixture
import woowacourse.payments.ui.cards.state.CardsViewModel

class CardsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `존재하는_카드가_없다면_등록_권유_문구와_카드_추가_박스가_보여진다`() {
        // given && when
        composeTestRule.setContent {
            CardsScreen(
                onRegistrationClick = {},
                onCardClick = {},
                viewModel = CardsViewModel(FakeCardsRepositoryFixture(emptyList())),
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
        // given
        val viewModel = CardsViewModel(FakeCardsRepositoryFixture(listOf(CARD_ONE)))

        // when
        composeTestRule.setContent {
            CardsScreen(
                onRegistrationClick = {},
                onCardClick = {},
                viewModel = viewModel,
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
        // given
        val viewModel = CardsViewModel(FakeCardsRepositoryFixture(listOf(CARD_ONE, CARD_TWO)))

        // when
        composeTestRule.setContent {
            CardsScreen(
                onRegistrationClick = {},
                onCardClick = {},
                viewModel = viewModel,
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
