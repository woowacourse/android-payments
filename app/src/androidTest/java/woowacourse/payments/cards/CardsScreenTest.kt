package woowacourse.payments.cards

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.state.CardState
import woowacourse.payments.ui.view.cards.CardsScreen
import woowacourse.payments.ui.view.cards.CardsUiState

class CardsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `등록된_카드가_없으면_새로운_카드를_등록해주세요와_기본_카드_이미지가_보인다`() {
        composeTestRule.setContent {
            CardsScreen(
                uiState = CardsUiState.EMPTY,
                onClickAddCard = {},
                onClickModifyCard = { _, _ -> },
            )
        }

        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("이 카드 이미지를 클릭해 새로운 카드를 추가해 주세요")
            .assertIsDisplayed()
    }

    @Test
    fun `등록된_카드가_한장_있으면_새로운_카드를_등록해주세요가_보이지_않고_실물_카드와_기본카드가_하나보인다`() {
        val card =
            Card(
                number = "1111222233334444",
                expireDate = "0421",
                ownerName = "peto",
                password = "",
                company = CardCompany.BC,
            )

        composeTestRule.setContent {
            CardsScreen(
                uiState = CardsUiState.SINGLE(card),
                onClickAddCard = {},
                onClickModifyCard = { _, _ -> },
            )
        }

        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText("1111 - 2222 - **** - ****")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("이 카드 이미지를 클릭해 새로운 카드를 추가해 주세요")
            .assertIsDisplayed()
    }

    @Test
    fun `등록된_카드가_한장_초과면_새로운_카드를_등록해주세요와_기본_카드가_보이지_않고_실물_카드_이미지만_보인다`() {
        val cards =
            listOf(
                Card("1111222233334444", "0908", "peto", "", CardCompany.BC),
                Card("2222333344445555", "0908", "peto", "", CardCompany.BC),
                Card("3333444455556666", "0908", "peto", "", CardCompany.BC),
            )

        composeTestRule.setContent {
            CardsScreen(
                uiState = CardsUiState.MULTIPLE(cards),
                onClickAddCard = {},
                onClickModifyCard = { _, _ -> },
            )
        }

        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithContentDescription("이 카드 이미지를 클릭해 새로운 카드를 추가해 주세요")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText("1111 - 2222 - **** - ****")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("2222 - 3333 - **** - ****")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("3333 - 4444 - **** - ****")
            .assertIsDisplayed()
    }

    @Test
    fun `빈_카드_클릭_시_onClickAddCard가_호출된다`() {
        var clicked = false
        composeTestRule.setContent {
            CardsScreen(
                uiState = CardsUiState.EMPTY,
                onClickAddCard = { clicked = true },
                onClickModifyCard = { _, _ -> },
            )
        }

        composeTestRule
            .onNodeWithContentDescription("이 카드 이미지를 클릭해 새로운 카드를 추가해 주세요")
            .performClick()

        assertEquals(true, clicked)
    }

    @Test
    fun `SINGLE_카드_클릭_시_onClickModifyCard가_호출된다`() {
        val card =
            Card("1111222233334444", "0421", "peto", "", CardCompany.BC)

        var clickedCard: CardState.Registered? = null

        composeTestRule.setContent {
            CardsScreen(
                uiState = CardsUiState.SINGLE(card),
                onClickAddCard = {},
                onClickModifyCard = { c, _ -> clickedCard = c as CardState.Registered },
            )
        }

        composeTestRule
            .onNodeWithText("1111 - 2222 - **** - ****")
            .performClick()

        assertEquals(card.number, clickedCard?.card?.number)
    }

    @Test
    fun `MULTIPLE_카드_클릭_시_onClickModifyCard가_호출된다`() {
        val cards =
            listOf(
                Card("1111222233334444", "0421", "peto", "", CardCompany.BC),
                Card("2222333344445555", "0522", "peto", "", CardCompany.BC),
            )

        var clickedCard: CardState.Registered? = null

        composeTestRule.setContent {
            CardsScreen(
                uiState = CardsUiState.MULTIPLE(cards),
                onClickAddCard = {},
                onClickModifyCard = { c, _ -> clickedCard = c as CardState.Registered },
            )
        }

        composeTestRule
            .onNodeWithText("1111 - 2222 - **** - ****")
            .performClick()

        assertEquals(cards[0].number, clickedCard?.card?.number)
    }
}
