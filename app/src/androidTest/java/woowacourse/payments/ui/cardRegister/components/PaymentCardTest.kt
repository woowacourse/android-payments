package woowacourse.payments.ui.cardRegister.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.model.Card

class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드가_null_일_때_기본_카드_모양이_보인다`() {
        // given:
        composeTestRule.setContent {
            PaymentCard(card = null)
        }

        // then:
        composeTestRule.onRoot().assertExists()
    }

    @Test
    fun `카드가_있을_때_카드번호와_만료일_소유자가_표시된다`() {
        // given:
        val card =
            Card(
                number = "1111222233334444",
                expiredDate = "0421",
                ownerName = "CREW",
                password = "1234",
            )
        composeTestRule.setContent {
            PaymentCard(card = card)
        }

        // then:
        composeTestRule.onNodeWithText("1111 - 2222 - **** - ****").assertIsDisplayed()
        composeTestRule.onNodeWithText("CREW").assertIsDisplayed()
        composeTestRule.onNodeWithText("04 / 21").assertIsDisplayed()
    }

    @Test
    fun `카드_소유자가_없을_때_카드번호_만료일만_표시된다`() {
        // given:
        val card =
            Card(
                number = "1111222233334444",
                expiredDate = "0421",
                ownerName = null,
                password = "1234",
            )
        composeTestRule.setContent {
            PaymentCard(card = card)
        }

        // then:
        composeTestRule.onNodeWithText("1111 - 2222 - **** - ****").assertIsDisplayed()
        composeTestRule.onNodeWithText("04 / 21").assertIsDisplayed()
    }
}
