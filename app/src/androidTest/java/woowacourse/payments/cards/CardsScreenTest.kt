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
import woowacourse.payments.ui.core.Event
import woowacourse.payments.ui.state.CardState
import woowacourse.payments.ui.view.cards.CardScreenUiEvent
import woowacourse.payments.ui.view.cards.CardsScreen
import woowacourse.payments.ui.view.cards.CardsUiState

class CardsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `등록된_카드가_없으면_새로운_카드를_등록해주세요와_기본_카드_이미지가_보인다`() {
        // given
        composeTestRule.setContent {
            CardsScreen(
                CardsUiState.EMPTY,
                Event(CardScreenUiEvent.Idle),
                {},
                {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("이 카드 이미지를 클릭해 새로운 카드를 추가해 주세요")
            .assertIsDisplayed()
    }

    @Test
    fun `등록된_카드가_한장_있으면_새로운_카드를_등록해주세요가_보이지_않고_실물_카드와_기본카드가_하나보인다`() {
        // given
        val uiState =
            CardsUiState.SINGLE(
                Card(
                    number = "1111222233334444",
                    expireDate = "0421",
                    ownerName = "peto",
                    password = "",
                    company = CardCompany.BC,
                ),
            )

        // when
        composeTestRule.setContent {
            CardsScreen(
                uiState,
                Event(CardScreenUiEvent.Idle),
                {},
                {},
            )
        }

        // then
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
        // given
        val uiState =
            CardsUiState.MULTIPLE(
                listOf(
                    Card(
                        number = "1111222233334444",
                        expireDate = "0908",
                        ownerName = "peto",
                        password = "",
                        CardCompany.BC,
                    ),
                    Card(
                        number = "2222333344445555",
                        expireDate = "0908",
                        ownerName = "peto",
                        password = "",
                        CardCompany.BC,
                    ),
                    Card(
                        number = "3333444455556666",
                        expireDate = "0908",
                        ownerName = "peto",
                        password = "",
                        CardCompany.BC,
                    ),
                ),
            )

        // when
        composeTestRule.setContent {
            CardsScreen(
                uiState,
                Event(CardScreenUiEvent.Idle),
                {},
                {},
            )
        }

        // then
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
    fun `빈_카드_클릭_시_onClickCard가_호출된다`() {
        // given
        var clickedType: CardState? = null
        composeTestRule.setContent {
            CardsScreen(
                CardsUiState.EMPTY,
                Event(CardScreenUiEvent.Idle),
                { clickedType = it },
                {},
            )
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("이 카드 이미지를 클릭해 새로운 카드를 추가해 주세요")
            .performClick()

        // then
        assertEquals(CardState.Empty, clickedType)
    }

    @Test
    fun `SINGLE_카드_클릭_시_onClickModifyCard_호출된다`() {
        var clickedCard: CardState? = null
        val uiState =
            CardsUiState.SINGLE(
                Card(
                    number = "1111222233334444",
                    expireDate = "0421",
                    ownerName = "peto",
                    password = "",
                    company = CardCompany.BC,
                ),
            )

        composeTestRule.setContent {
            CardsScreen(
                uiState,
                Event(CardScreenUiEvent.Idle),
                onClickAddCard = {},
                onClickModifyCard = { clickedCard = it },
            )
        }

        composeTestRule
            .onNodeWithText("1111 - 2222 - **** - ****")
            .performClick()

        assertEquals(uiState.state.number, (clickedCard as CardState.Registered).card.number)
    }

    @Test
    fun `MULTIPLE_카드_클릭_시_onClickModifyCard_호출된다`() {
        var clickedCard: CardState? = null
        val cards =
            listOf(
                Card("1111222233334444", "0421", "peto", "", CardCompany.BC),
                Card("2222333344445555", "0522", "peto", "", CardCompany.BC),
            )
        val uiState = CardsUiState.MULTIPLE(cards)

        composeTestRule.setContent {
            CardsScreen(
                uiState,
                Event(CardScreenUiEvent.Idle),
                onClickAddCard = {},
                onClickModifyCard = { clickedCard = it },
            )
        }

        composeTestRule
            .onNodeWithText("1111 - 2222 - **** - ****")
            .performClick()

        assertEquals(cards[0].number, (clickedCard as CardState.Registered).card.number)
    }
}
