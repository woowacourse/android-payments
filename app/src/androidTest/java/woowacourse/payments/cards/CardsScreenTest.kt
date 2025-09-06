package woowacourse.payments.cards

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class CardsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드_목록_상단엔_Payments_텍스트가_보인다`() {
        // given
        composeTestRule.setContent {
            CardsScreen()
        }
        val title = "Payments"

        // then
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }
}
