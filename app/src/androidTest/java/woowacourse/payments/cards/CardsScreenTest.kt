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
import woowacourse.payments.domain.CardType

class CardsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `등록된_카드가_없으면_새로운_카드를_등록해주세요와_기본_카드_이미지가_보인다`() {
        // given
        composeTestRule.setContent {
            CardsScreen(
                cards = emptyList(),
                onCompleteAddCard = true,
                onClickCard = {},
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
        val cards = listOf(
            Card(
                number = "1111222233334444",
                expireDate = "0421",
                ownerName = "peto",
                password = ""
            )
        )

        // when
        composeTestRule.setContent {
            CardsScreen(cards, false, {})
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
        val cards = listOf(
            Card(
                number = "1111222233334444",
                expireDate = "0908",
                ownerName = "peto",
                password = ""
            ),
            Card(
                number = "2222333344445555",
                expireDate = "0908",
                ownerName = "peto",
                password = ""
            ),
            Card(
                number = "3333444455556666",
                expireDate = "0908",
                ownerName = "peto",
                password = ""
            )
        )

        // when
        composeTestRule.setContent {
            CardsScreen(cards, false, {})
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
        var clickedType: CardType? = null
        composeTestRule.setContent {
            CardsScreen(emptyList(), false, onClickCard = { clickedType = it })
        }

        composeTestRule
            .onNodeWithContentDescription("이 카드 이미지를 클릭해 새로운 카드를 추가해 주세요")
            .performClick()

        assertEquals(CardType.EMPTY, clickedType)
    }
}
