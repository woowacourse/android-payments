package woowacourse.payments.cards

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            CardsScreen()
        }
    }

    @Test
    fun `카드_목록_상단엔_Payments_텍스트가_보인다`() {
        // given
        val title = "Payments"

        // then
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun `새로운_카드를_등록해주세요_텍스트가_보인다`(){
        // given
        val title = "새로운 카드를 등록해주세요"

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }
}
