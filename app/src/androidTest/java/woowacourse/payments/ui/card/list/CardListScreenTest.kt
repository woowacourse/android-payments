package woowacourse.payments.ui.card.list

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.payments.ui.model.CardUiModel

@RunWith(AndroidJUnit4::class)
class CardListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드가_없을_때_빈_화면과_추가_버튼을_보여준다`() {
        // Given
        val emptyCards = emptyList<CardUiModel>()

        // When
        composeTestRule.setContent {
            CardListScreen(cards = emptyCards)
        }

        // Then
        composeTestRule.onNodeWithText("새로운 카드를 등록해주세요").assertExists()
        composeTestRule.onNodeWithText("추가").assertDoesNotExist()
    }

    @Test
    fun `카드가_하나일_때_단일_카드_화면을_보여준다`() {
        // Given
        val oneCard = listOf(CardUiModel("1111222233334444", "12/25", "TAMA SEO"))

        // When
        composeTestRule.setContent {
            CardListScreen(cards = oneCard)
        }

        // Then
        composeTestRule.onNodeWithText("1111222233334444").assertExists()
        composeTestRule.onNodeWithText("TAMA SEO").assertExists()
        composeTestRule.onNodeWithText("추가").assertDoesNotExist()
    }

    @Test
    fun `카드가_여러_개일_때_목록_화면과_추가_버튼을_보여준다`() {
        // Given
        val multipleCards =
            listOf(
                CardUiModel("1111222233334444", "12/25", "TAMA SEO"),
                CardUiModel("5555666677778888", "01/26", "WOOAH"),
            )

        // When
        composeTestRule.setContent {
            CardListScreen(cards = multipleCards)
        }

        // Then
        composeTestRule.onNodeWithText("1111222233334444").assertExists()
        composeTestRule.onNodeWithText("5555666677778888").assertExists()
        composeTestRule.onNodeWithText("추가").assertExists()
    }
}
