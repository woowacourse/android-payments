package woowacourse.payments.ui.cardList

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.common.model.CardCompanyUiType
import woowacourse.payments.ui.common.model.CardUiModel

class CardListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드_없을_때_안내_텍스트와_카드추가_버튼이_보인다`() {
        // given:
        var clicked = false
        composeTestRule.setContent {
            CardListScreen(
                cards = emptyList(),
                onRegistrationClick = { clicked = true },
            )
        }

        // then:
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("카드 추가 버튼")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("카드 추가 버튼")
            .performClick()

        assertThat(clicked).isEqualTo(true)
    }

    @Test
    fun `카드_1개일_때_카드_정보와_등록_버튼이_보인다`() {
        // given:
        val card =
            CardUiModel(
                number = "1111222233334444",
                expiredDate = "0421",
                ownerName = "CREW",
                password = "1234",
                cardCompany = CardCompanyUiType.BC,
            )
        var clicked = false
        composeTestRule.setContent {
            CardListScreen(
                cards = listOf(card),
                onRegistrationClick = { clicked = true },
            )
        }

        // then:
        composeTestRule.onNodeWithText("1111 - 2222 - **** - ****").assertIsDisplayed()
        composeTestRule.onNodeWithText("CREW").assertIsDisplayed()
        composeTestRule.onNodeWithText("04 / 21").assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("카드 추가 버튼")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("카드 추가 버튼")
            .performClick()

        assertThat(clicked).isEqualTo(true)
    }

    @Test
    fun `카드_2개_이상일_때_등록_버튼은_보이지_않고_상단의_추가_버튼이_보인다`() {
        // given:
        val cards =
            listOf(
                CardUiModel(
                    "1111222233334444",
                    "0421",
                    "CREW",
                    "1234",
                    cardCompany = CardCompanyUiType.HANA,
                ),
                CardUiModel(
                    "5555666677778888",
                    "0522",
                    "Moong",
                    "5678",
                    cardCompany = CardCompanyUiType.KAKAO,
                ),
            )
        composeTestRule.setContent {
            CardListScreen(cards = cards, onRegistrationClick = {})
        }

        // then:
        composeTestRule.onNodeWithText("1111 - 2222 - **** - ****").assertIsDisplayed()
        composeTestRule.onNodeWithText("CREW").assertIsDisplayed()
        composeTestRule.onNodeWithText("04 / 21").assertIsDisplayed()

        composeTestRule.onNodeWithText("5555 - 6666 - **** - ****").assertIsDisplayed()
        composeTestRule.onNodeWithText("Moong").assertIsDisplayed()
        composeTestRule.onNodeWithText("05 / 22").assertIsDisplayed()

        composeTestRule
            .onNodeWithText("+")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText("추가")
            .assertIsDisplayed()
    }
}
