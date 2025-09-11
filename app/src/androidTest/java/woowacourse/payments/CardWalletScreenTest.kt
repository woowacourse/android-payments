package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.cardwallet.CardWalletScreen

class CardWalletScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Payments라는_텍스트가_표시된다`() {
        composeTestRule.setContent {
            CardWalletScreen(cardList = emptyList(), onCardAddResult = {
            })
        }
        composeTestRule.onNodeWithText("Payments").assertIsDisplayed()
    }

//    @Test
//    fun `등록된_카드가_없을 때`

}
