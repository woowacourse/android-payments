package woowacourse.payments.ui.cardlist.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class PaymentCardListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupPaymentCardList(content: @Composable () -> Unit = { Text("Test Content") }) {
        composeTestRule.setContent {
            PaymentCard(content = content)
        }
    }

    @Test
    fun `PaymentCard_내부_콘텐츠가_표시된다`() {
        // given + when
        setupPaymentCardList()

        // then
        composeTestRule.onNodeWithText("Test Content").assertIsDisplayed()
    }
}
