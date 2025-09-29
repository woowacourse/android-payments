package woowacourse.payments.ui.cardlist.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PaymentCardListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            PaymentCard(content = { Text("Test Content") })
        }
    }

    @Test
    fun `PaymentCard_내부_콘텐츠가_표시된다`() {
        // given + when
        composeTestRule.onNodeWithText("Test Content").assertIsDisplayed()

        // then
    }
}
