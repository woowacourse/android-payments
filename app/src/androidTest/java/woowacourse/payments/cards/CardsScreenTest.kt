package woowacourse.payments.cards

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class CardsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드_목록_상단엔_Payments_텍스트가_보인다`() {
        composeTestRule.setContent {
            CardsScreen(emptyList())
        }

        // given
        val title = "Payments"

        // then
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun `등록된_카드가_없으면_새로운_카드를_등록해주세요와_기본_카드_이미지가_보인다`() {
        // given
        composeTestRule.setContent {
            CardsScreen(emptyList())
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("이 기본 카드 이미지를 클릭해 새로운 카드를 추가해 주세요")
            .assertIsDisplayed()
    }
}
