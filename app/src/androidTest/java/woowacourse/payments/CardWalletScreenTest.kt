package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class CardWalletScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Payments라는_텍스트가_표시된다`() {
        composeTestRule.setContent {
            CardWalletScreen(cardList,)
        }
        composeTestRule.onNodeWithText("Payments").assertIsDisplayed()
    }

}
